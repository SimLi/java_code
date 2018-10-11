package version2.v2ch01.simoncode;

import java.io.*;

/**
 * Created by sim on 2017/3/5.
 */
public class ObjectStreamTest {

    public static void main(String[] args){
        EmployeeTest[] empls = new EmployeeTest[3];

        EmployeeTest simon = new EmployeeTest("Simon","男",28,18000);
        EmployeeTest robin = new EmployeeTest("Robin.He","男",30,18000);
        ManagerTest  barry = new ManagerTest("Barry.Zhu","男",40,20000,2000);
        barry.setMishu(robin);

        empls[0] = simon;
        empls[1] = robin;
        empls[2] = barry;

        try {
            //对象写入到文件中
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("employObject.dat"));
            outputStream.writeObject(empls);

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("employObject.dat"));
            EmployeeTest[] emps =  (EmployeeTest[]) inputStream.readObject();
            for(EmployeeTest em : emps){
                System.out.println(em);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

class EmployeeTest implements Serializable {

    private String name;
    private String sex;
    private int age;
    private double salary;

    public EmployeeTest() {
    }

    public EmployeeTest(String name, String sex, int age, double salary) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeTest{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

class ManagerTest extends EmployeeTest{
    private double jingjin;

    private EmployeeTest mishu;

    public EmployeeTest getMishu() {
        return mishu;
    }

    public void setMishu(EmployeeTest mishu) {
        this.mishu = mishu;
    }

    public ManagerTest(String name, String sex, int age, double salary, double jingjin) {
        super(name, sex, age, salary);
        this.jingjin = jingjin;
    }

    @Override
    public String toString() {
        return "ManagerTest{" +
                "jingjin=" + jingjin +
                ", mishu=" + mishu +
                '}';
    }

    public ManagerTest() {
    }
}
