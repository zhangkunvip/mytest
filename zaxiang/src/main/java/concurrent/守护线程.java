package concurrent;

public class 守护线程 {

    public static void main(String args[]) throws InterruptedException {
        Thread thread = new MyThread();
//        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);
//        thread.stop();
    }
}

class MyThread extends Thread {
    private int i = 0;

    public void run() {
        super.run();
        try {
            while (true) {
                System.out.println("i=" + i);
                i++;
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}