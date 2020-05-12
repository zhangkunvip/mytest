package 算法;

public class volatile和syn {
    private static final int TOTAL = 10000;

    //	即便像下面这样加了 volatile 关键字修饰不会解决问题，因为并没有解决原子性问题
    private volatile int count;

    public static void main(String[] args) {
        volatile和syn volatile和syn = new volatile和syn();

        Thread thread1 = new Thread(() -> volatile和syn.add10KCount());
        Thread thread2 = new Thread(() -> volatile和syn.add10KCount());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("count 值为：{}" + volatile和syn.count);

    }

//    private void add10KCount() {
//        int start = 0;
//        while (start++ < TOTAL) {
//            this.count++;
//        }
//    }

    //synchronized
    private synchronized void add10KCount() {
        int start = 0;
        while (start++ < TOTAL) {
            this.count++;
        }
        System.out.println(Thread.currentThread().getName() + "count 值为：" + count);
    }

}