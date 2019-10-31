package thread.jointhread;

/**
 * PROJECT_name: github
 * package: thread
 * creat_user: zhangkun19
 * creat_date: 2019/07/15
 * creat_time: 11:23
 * describe: TODO
 **/
public class JoinThreadMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始！");
        JoinThread jt1 = new JoinThread("a");
        JoinThread jt2 = new JoinThread("b");
        jt1.start();
        jt2.start();
        try {
            jt1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            jt2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "主线程运行结束！");
    }
}
