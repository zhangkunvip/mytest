package thread.jointhread;

/**
 * PROJECT_name: github
 * package: thread
 * creat_user: zhangkun19
 * creat_date: 2019/07/15
 * creat_time: 11:23
 * describe: TODO
 **/
public class JoinThread extends Thread {
    private String name;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, gname)}, where {@code gname} is a newly generated
     * name. Automatically generated names are of the form
     * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
     */
    public JoinThread(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程运行开始！");
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程" + name + "运行：" + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "线程运行结束！");
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始！");
        JoinThread jt1 = new JoinThread("a");
        JoinThread jt2 = new JoinThread("b");
        jt1.start();
        jt2.start();
        System.out.println(Thread.currentThread().getName() + "主线程运行结束！");
    }
}
