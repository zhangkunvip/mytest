package thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * PROJECT_name: github
 * package: thread
 * creat_user: zhangkun19
 * creat_date: 2019/07/17
 * creat_time: 18:12
 * describe: TODO
 **/
public class ForkJoinThread extends RecursiveTask<Integer> {

    public static final int THRESHLD = 10;
    private int start;
    private int end;


    public ForkJoinThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) < THRESHLD) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) >>> 1;
            ForkJoinThread left = new ForkJoinThread(start, middle);
            ForkJoinThread right = new ForkJoinThread(middle + 1, end);
            left.fork();
            right.fork();
            sum = left.join();
             right.join();
            System.out.println(Thread.currentThread().getName() + "-----" + sum);
        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new ForkJoinThread(1, 1000));
        System.out.println(result.get());
    }
}
