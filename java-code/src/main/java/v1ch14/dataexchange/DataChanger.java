package v1ch14.dataexchange;

import java.util.concurrent.Exchanger;

/**
 * Created by sim on 2017/3/2.
 * 两个线程实现数据交换  Exchanger 实现
 */
public class DataChanger {
    public final Exchanger<StringBuilder> sbChange = new Exchanger<>();

    public class FirstThread implements Runnable{
        @Override
        public void run() {
            StringBuilder context = new StringBuilder("Hello ");
            System.out.println("交换之前 ："+ context.toString());
            try {
                context = sbChange.exchange(context);
                System.out.println("线程： "+Thread.currentThread().getId()+" context 交换以后的值是"+ context.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class SecondThread implements Runnable{
        @Override
        public void run() {
            StringBuilder sn_context = new StringBuilder(" World !");
            System.out.println("sn_context 交换之前的数据是"+sn_context.toString());
            try {
                sn_context = sbChange.exchange(sn_context);
                System.out.println("线程 : "+ Thread.currentThread().getId() + "sn_context  交换以后的数据是 "+sn_context.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    public  void start(){
        new Thread(new FirstThread()).start();
        new Thread(new SecondThread()).start();
    }

    public static void main(String[] args){
        new DataChanger().start();
    }


}
