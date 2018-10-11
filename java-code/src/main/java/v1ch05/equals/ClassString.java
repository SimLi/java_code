package v1ch05.equals;

/**
 * Created by sim on 2016/12/16.
 */
public class ClassString {

    public static void testaToString(TestA a){
        System.out.println(a.getArg1()+"---->"+a.getArg2());
        System.out.println(a.toString());
        System.out.println("arg1==="+a.getArg1().toString());
        System.out.println("arg2==="+a.getArg2());
    }

    public static void main(String[] args0){
        TestA a = new TestA();
        a.setArg1("hhh");
        a.setArg2(10);
        System.out.println(a.toString());
        testaToString(a);

    }
}

class  TestA{
    private String arg1;
    private int arg2;

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public int getArg2() {
        return arg2;
    }

    public void setArg2(int arg2) {
        this.arg2 = arg2;
    }
}
