package thread;

public class WaitSleepDemo {

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(() -> {
            System.out.println("Thread A is waiting to get lock");
            synchronized (lock) {
                try {
                    System.out.println("Thread A get lock");
//                    Thread.sleep(1000);
                    lock.wait(1000);
                    System.out.println("Thread A is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 为了先执行Thread A 再执行 Thread B
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("Thread B is waiting to get lock");
            synchronized (lock) {
                try {
                    System.out.println("Thread B get lock");
//                    lock.wait(1000);
                    Thread.sleep(1000);
                    System.out.println("Thread B is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}