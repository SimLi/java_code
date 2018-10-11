package v1ch05.simTest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by sim on 2016/12/16.
 * @author sim
 */
public class FeildAndMethod {

    public static Object reflecFeild( Object cl, String feildName,Object value){
        try {
            Field f = cl.getClass().getDeclaredField(feildName);
            f.setAccessible(true);
            f.set(cl,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object reflecFieldGet(Object obj,String fieldName){
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object reflecMethodInvok(Object object,String methodName,Object... params){
        try {
            Method m = null;
            if (params != null){
                Class[] classes = new Class[params.length];
                int i=0;
                for (Object o : params){
                    classes[i] = o.getClass();
                    i++;
                }
                m=object.getClass().getDeclaredMethod(methodName,classes);
            }else {
               m = object.getClass().getDeclaredMethod(methodName);
            }


            return m.invoke(object,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args0){
        Employee ee = new Employee();
        FeildAndMethod.reflecFeild(ee,"name","我爱阿昭");
        System.out.println(ee.getName());
        System.out.println("reflecFieldGet == "+FeildAndMethod.reflecFieldGet(ee,"name"));
        FeildAndMethod.reflecMethodInvok(ee,"setName","我的爱啊");
        System.out.println("reflecMethodInvok以后的值==="+FeildAndMethod.reflecFieldGet(ee,"name"));
        System.out.println(FeildAndMethod.reflecMethodInvok(ee,"getName",null));
    }

}

class Employee {
    private String name;
    private int age;
    private double salay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalay() {
        return salay;
    }

    public void setSalay(double salay) {
        this.salay = salay;
    }
}
