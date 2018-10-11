package v1ch14.simoncode;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sim on 2017/3/2.
 * 查找给定路径下，文件中的指定的关键字
 * 多线程实现
 */
public class SearchKeyWordBlockQueue {



    //测试查找
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件路径：");
        String path = scanner.nextLine();
        System.out.println("请输入要查找的关键字:");
        String keyWord = scanner.nextLine();

        final int LINK_SIZE = 10;
        final int SEARCH_THREADS = 4;
        BlockingQueue<File> queue = new LinkedBlockingQueue(LINK_SIZE);
        new Thread(new FileEnumerationTask(queue, path)).start();
        for(int i=0;i<SEARCH_THREADS;i++){
            new Thread(new SearchKeyWordThread(queue,keyWord)).start();
        }

    }

}


    //查找关键字的线程实现
    class SearchKeyWordThread implements Runnable{

        private BlockingQueue<File> queue;
        String keyword;

        public SearchKeyWordThread(BlockingQueue<File> queue, String keyword) {
            this.queue = queue;
            this.keyword = keyword;
        }

        @Override
        public void run() {
            try {
                boolean done = true;
                while (done){
                    File f = queue.take();
                    if (f == FileEnumerationTask.EMPTY_FILE){
                        done = false;
                        queue.put(FileEnumerationTask.EMPTY_FILE);
                    }else{
                        seach(f);
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (IOException ie){

            }
        }

        /**
         * 读取数据，查找关键字
         * */
        public void seach(File f ) throws IOException{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String readLine = null;
            int lineNum = 1;
            while ((readLine = reader.readLine()) != null){
                if (readLine.contains(keyword)){

                    System.out.printf(" 文件%s, 在%d行处,第%d字符出现关键字%s %n",f.getParent()+f.getName(),lineNum,
                            readLine.indexOf(keyword),keyword);
                    lineNum++;
                }
            }
        }

    }


    //递归向队列中放入文件
    class FileEnumerationTask implements Runnable {
        BlockingQueue<File> queue;
        String pathname;
        static final File EMPTY_FILE = new File("");

        public FileEnumerationTask(BlockingQueue<File> queue,String pathname) {
            this.queue = queue;
            this.pathname = pathname;
        }

        @Override
        public void run() {
            File directory = new File(pathname);
            try {
                enumerFile(directory);
                queue.put(EMPTY_FILE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public void enumerFile(File patch) throws InterruptedException {
            for(File fls : patch.listFiles()){
                if (fls.isDirectory())
                    enumerFile(fls);
                queue.put(fls);
            }
        }


    }


