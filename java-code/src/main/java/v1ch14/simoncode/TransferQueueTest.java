package v1ch14.simoncode;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by sim on 2017/3/3.
 * 测试TransferQueue，
 */
public class TransferQueueTest {
    public static void main(String[] args){
        TransferQueue transferQueue = new LinkedTransferQueue();
        new Thread(new ProductThread(transferQueue)).start();
//        new Thread(new Customer(transferQueue)).start();
    }


}

 class ProductThread implements Runnable {

    TransferQueue transferQueue;

    public ProductThread() {
    }

     public ProductThread(TransferQueue transferQueue) {
         this.transferQueue = transferQueue;
     }

     @Override
     public void run() {
         try {
             boolean done = true;
             int count = 0;
             while (done){
                 if (count == 10){
                     done = false;
                     transferQueue.transfer("OVER");
                 }


                 System.out.println("transferQueue.transfer()"+count);
                 transferQueue.transfer("这是个阻塞进程"+count);//线程执行改行以后，会在此处进行阻塞，直到有线程取出tq中的元素，线程才向后执行
                 System.out.println("transfer以后"+count);
                 count++;


             }


         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
 }

 class Customer implements Runnable{

    TransferQueue<String> transferQueue;

     public Customer(TransferQueue transferQueue) {
         this.transferQueue = transferQueue;
     }

     @Override
     public void run() {
         try {
             boolean done = true;
             while (done){
                 String  info = transferQueue.take();
                 if ("OVER".equals(info)){
                     done = true;
                 }else {
                     System.out.println("消费者取出了信息："+info);
                 }
             }
         } catch (InterruptedException e) {
             e.printStackTrace();
         }

     }
 }
