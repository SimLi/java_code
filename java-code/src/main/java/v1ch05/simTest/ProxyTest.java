package v1ch05.simTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by sim on 2016/12/17.
 */
public class ProxyTest {
    public static void main(String[] args){
        final Object object;
        try {
            object = Class.forName(ProxyObject.class.getName()).newInstance();
            InvocationHandler handler = new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("执行代理类"+proxy.getClass()+"---->方法名称是"+method.getName());
                    return method.invoke(object,args);
                }
            };
            ProxyInter prox = (ProxyInter)Proxy.newProxyInstance(ProxyObject.class.getClassLoader(),
                    ProxyObject.class.getInterfaces(),handler);
//            prox.test01(null);
            Method[] ms = prox.getClass().getDeclaredMethods();
            for (Method m : ms){
                if (m.getName().equals("test01")){
                    m.setAccessible(true);
                    m.invoke(prox,"aa");
                }
                System.out.println(m.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ProxyObject implements ProxyInter{
    private String name;
    private String address;
    private int age;

    /**
     * 测试代理执行的过程
     * */
    public String test01(Object obj){
        System.out.println("执行了这个方法=="+obj.toString());
        return "";
    }

    @Override
    public String test02() {
        System.out.println("测试执行方法02");
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
