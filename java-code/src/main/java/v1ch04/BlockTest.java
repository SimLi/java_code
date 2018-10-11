package v1ch04;

public class BlockTest {
    static
    {
        System.out.println("Hello Word! this is static block我的信息在这里");
        System.out.println();
    }
    
    {
        System.out.println("Hello Word! this is  block");
    }
    
    public static void main(String[] args0){
        for (int i=0; i<10; i++){
            System.out.println("这是我的循环体执行");
            System.out.println(i);
        }

        BlockTest cl = new BlockTest();
    }
}
