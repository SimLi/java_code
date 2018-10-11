package v1ch14.simoncode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by sim on 2017/3/4.
 * 线程执行器与线程池的使用
 * 文件中查找关键字，计数文件总数 call实现
 */
public class ExecutAndThreadPool {


    public static void main(String[] args0){
        Scanner in = new Scanner(System.in);
        System.out.print("请输入查找路径:");
        String path = in.nextLine();
        System.out.print("请输入查找的关键词：");
        String keyWord = in.nextLine();
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        ExecuxCountFileNum countFileNum = new ExecuxCountFileNum(path,keyWord,poolExecutor);
        Future<Integer> result =  poolExecutor.submit(countFileNum);
        try {
            System.out.println("文件数量是："+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        poolExecutor.shutdown();
        System.out.print("Thread num is :"+poolExecutor.getLargestPoolSize());

    }




}

class ExecuxCountFileNum implements Callable<Integer>{

    private String path;
    private String keyWord;
    private ThreadPoolExecutor poolExecutor;
    private Integer count ;
    public ExecuxCountFileNum(String path, String keyWord, ThreadPoolExecutor poolExecutor) {
        this.path = path;
        this.keyWord = keyWord;
        this.poolExecutor = poolExecutor;
    }

    @Override
    public Integer call() throws Exception {
        count = 0;
        List<Future<Integer>> futures = new ArrayList<>();
        File file = new File(path);
        File[] files = file.listFiles();
        for (File fl : files){
            if (fl.isDirectory()){
                ExecuxCountFileNum countFileNum = new ExecuxCountFileNum(fl.getPath(),keyWord,poolExecutor);
                Future<Integer> result = poolExecutor.submit(countFileNum);
                futures.add(result);

            }else {
                if (findkeyWord(fl)) count++;
            }
        }


        for(Future<Integer> f : futures){
            count +=f.get();
        }



        return count;
    }

    private boolean findkeyWord(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String lineInfo = null;
            while ((lineInfo = bufferedReader.readLine()) != null){
                if (lineInfo.contains(keyWord))
                    return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
