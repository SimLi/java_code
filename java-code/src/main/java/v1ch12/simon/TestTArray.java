package v1ch12.simon;

import v1ch12.pair2.Pair;

/**
 * Created by sim on 2017/4/25.
 */
public class TestTArray {
    public static void testArray(){
        Pair<String>[] t = new Pair[10];
        //Pair<String>[] t1 = new Pair<String>[10]; // ERROR
        t[0] = new Pair<String>("a","c");
        System.out.println(t[0]);
    }

    public static void main(String[] args0){
        testArray();
    }
}
