package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * PROJECT_name: mytest
 * package: concurrent
 * creat_user: zhangkun19
 * creat_date: 2019/10/29
 * creat_time: 21:04
 * describe: TODO
 **/
public class ExecutorsTest {

    public static void main(String[] args) {

        String result ="";

        Callable<Object> callable =  Executors.callable(new call(),result);

        System.out.println("run");
    }
}

class call implements Runnable{

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("run");

    }
}
