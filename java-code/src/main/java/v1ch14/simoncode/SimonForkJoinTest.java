package v1ch14.simoncode;

import java.lang.annotation.Retention;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by sim on 2017/3/4.
 * forkJoin框架实现数组中，查找符合条件的内容
 */
public class SimonForkJoinTest {
    public static void main(String[] args){
        double[] values = new double[100000];
        for(int i=0;i<100000;i++){
            values[i] = Math.random();
        }

        CountInfo countInfo = new CountInfo(0, 100000, values, new Filter() {
            @Override
            public boolean filter(double value) {
                return value> 0.5;
            }
        });
        CountInfo.invokeAll(countInfo,countInfo);
        System.out.print(countInfo.join());

//        ForkJoinPool pool = new ForkJoinPool();
//        pool.invoke(countInfo);
//        System.out.print(countInfo.join());


    }

}

interface Filter{
    boolean filter(double value);
}

class CountInfo extends RecursiveTask<Integer> {
    private final int SIZE = 1000;
    private int start;
    private int end;
    private double[] values;
    Filter filter;

    public CountInfo(int start, int end, double[] values,Filter filter) {
        this.start = start;
        this.end = end;
        this.values = values;
        this.filter = filter;
    }


    @Override
    protected Integer compute() {
        if ((end - start)>this.SIZE){
            int mid = (start+end) / 2 ;
            CountInfo first = new CountInfo(start,mid,values,filter);
            CountInfo sceond = new CountInfo(mid,end,values,filter);
            invokeAll(first,sceond);
            return first.join() + sceond.join();
        }else{
            int count = 0;
            for ( int i = start;i<end;i++){
                if (filter.filter(values[i])) count++;
            }
            return count;
        }

    }
}

