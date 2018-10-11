package version2.v2ch01.simoncode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

/**
 * Created by sim on 2017/3/5.
 */
public class ZipStreamTest {


    private static void tesZipInputStream(String zipPath){
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipPath));
            Scanner sc = new Scanner(zipInputStream);
            while (sc.hasNextLine()){
                System.out.print(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args0){
        tesZipInputStream("D:\\技术文档\\java_api\\Thymeleaf中文文档.rar");
    }

}
