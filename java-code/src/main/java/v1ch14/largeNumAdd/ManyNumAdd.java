package v1ch14.largeNumAdd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by sim on 2017/3/2.
 * 一组连续的，很大的数字相加，多线程实现
 * 使用 倒数闸门 方式实现
 *
 *
 */
public class ManyNumAdd {
    private Long result = 0L;

    private List<Long> largeNum = new ArrayList();

    private class ThreadNumAdd implements Runnable{

        private List<Long> longNumList;
        private CountDownLatch singal;//总共的线程计数器

        private Long addnum = 0L;

        public ThreadNumAdd(List<Long> longNumList,CountDownLatch singal) {
            this.longNumList = longNumList;
            this.singal = singal;
        }

        public List<Long> getLongNumList() {
            return longNumList;
        }

        public void setLongNumList(List<Long> longNumList) {
            this.longNumList = longNumList;
        }

        @Override
        public void run() {
            for (Long num : longNumList){
                addnum += num;
            }
            System.out.println("Name == "+Thread.currentThread().getName() + " id ="+Thread.currentThread().getId());
            result +=addnum;
            singal.countDown();//线程执行结束，总线程数减1
        }
    }

    public void productLargeNumList(Long num) throws InterruptedException {
        int threadNum = 0;
        if ((num % 1000)> 0){
            threadNum = Integer.parseInt(String.valueOf((num / 1000))) +1;
        }else{
            threadNum = Integer.parseInt(String.valueOf((num / 1000)));
        }
        System.out.println("threadNum == "+threadNum);
        List<Long> temp_num = new ArrayList<Long>();
        CountDownLatch sortSignal= new CountDownLatch(threadNum);
        int run_thread = 1;

        for (Long i= 1L;  i<=num;i++){
            if ( i >= (run_thread * 1000)){
                new Thread(new ThreadNumAdd(temp_num,sortSignal)).start();
                temp_num = new ArrayList<Long>();
                run_thread += 1;
            }
            temp_num.add(i);
        }
        if (temp_num.size()>0){
            new Thread(new ThreadNumAdd(temp_num,sortSignal)).start();
        }
        sortSignal.await();
        System.out.println("相加的结果集是：===》"+result);
    }



    public void classicalImpent(Long num){
        Long result = 0L;
        for (Long lo =1L; lo<=num; lo++ ){
            result += lo;
        }
        System.out.println("classicalImpent == "+result);
    }




    public static void main(String[] args){
        Long num = 10000L;
        if((num % 10000) > 0){

        }
        System.out.println(num / 10000);
        try {
            new ManyNumAdd().productLargeNumList(100000L);
            new ManyNumAdd().classicalImpent(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }





}
