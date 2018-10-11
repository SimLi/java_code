package simon.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sim on 2017/4/18.
 */
public class TestSysoutPrintWrite {

    public static void main(String[] args0){
        String name = "李小蒙";
        int age = 28;
        double gongzi = 18000.00;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("emplateTest.txt"),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw.write(name+System.getProperty("line.separator"));
        pw.write("age="+age);
        pw.print("gongzi="+gongzi);
        pw.println(name);
    }

}
