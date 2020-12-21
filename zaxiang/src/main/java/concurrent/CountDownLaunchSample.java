package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLaunchSample {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new playerOne(countDownLatch)).start();
        new Thread(new playerTwo(countDownLatch)).start();
        countDownLatch.await();
        System.out.println("全部加载完成");
    }

    static class playerOne implements Runnable {
        CountDownLatch countDownLatch;

        public playerOne(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                System.out.println("玩家1开始加载...");
                Thread.sleep(2000);
                System.out.println("玩家1加载完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (countDownLatch != null)
                    countDownLatch.countDown();
            }
        }

    }


    static class playerTwo implements Runnable {
        CountDownLatch countDownLatch;

        public playerTwo(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            try {
                System.out.println("玩家2开始加载...");
                Thread.sleep(1000);
                System.out.println("玩家2加载完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (countDownLatch != null)
                    countDownLatch.countDown();
            }
        }

    }
}