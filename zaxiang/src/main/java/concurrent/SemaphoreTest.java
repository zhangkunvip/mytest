package concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore,"xiaocaijishu"+i)).start();
        }
    }

    static class Task extends Thread{
        Semaphore semaphore;

        public Task(Semaphore semaphore,String tname){
            this.semaphore = semaphore;
            this.setName(tname);
        }

        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"拿着3D眼镜进去了,时间是"+System.currentTimeMillis());

                Thread.sleep(1000);

                semaphore.release();
                System.out.println(Thread.currentThread().getName()+"出来了,将3D眼镜还给了服务人员,时间是"+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
