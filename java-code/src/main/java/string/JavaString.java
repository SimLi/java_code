package string;

public class JavaString {
    
    public static void testStringLength(){
        String test = "；●ΠΞ∏ǘ";
        System.out.println(test.codePointCount(0, test.length()));
        System.out.println(String.format("hshsh in ( %s )", "2,3,6"));
    }
    
    public static void main(String[] args){
        testStringLength();
    }
}
