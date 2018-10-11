package v1ch14.simoncode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by sim on 2017/3/3.
 */
public class SimonFutureTest {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Input File Path:");
        String path = in.nextLine();
        System.out.print(" Input KeyWord : ");
        String keyWord = in.nextLine();
        CountFileNum fileNum = new CountFileNum(path,keyWord);
        FutureTask<Integer> task = new FutureTask<Integer>(fileNum);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println("File number is :"+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}

class CountFileNum implements Callable<Integer>{

    private String directoryPath;
    private String keyWord;
    private int count;

    public CountFileNum(String directoryPath, String keyWord) {
        this.directoryPath = directoryPath;
        this.keyWord = keyWord;
    }

    @Override
    public Integer call() throws Exception {
        File file = new File(directoryPath);
        File[] files= file.listFiles();
        List<FutureTask<Integer>> result = new ArrayList<FutureTask<Integer>>();

        for (File f : files){
            if (f.isDirectory()){
                CountFileNum fileNum = new CountFileNum(f.getPath(),keyWord);
                FutureTask<Integer> future = new FutureTask<>(fileNum);
                new Thread(future).start();
                result.add(future);
            }else {
                if(checkKeyWordFile(f)) count++;
            }
        }

        for (FutureTask<Integer> task : result){
             count += task.get();
        }


        return count;
    }


    public boolean checkKeyWordFile(File file) throws FileNotFoundException,IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readerInfo = null;
        while ((readerInfo = bufferedReader.readLine()) != null){
            if (readerInfo.contains(keyWord))
                return true;
        }

        return false;
    }

}
