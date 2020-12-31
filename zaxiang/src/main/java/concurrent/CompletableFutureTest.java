package concurrent;

import java.util.concurrent.*;

public class CompletableFutureTest {
    static String a = "aaa";
    static String b = "bbb";
    static String c = "ccc";

    public static void main(String[] args) {

        CompletableFuture.runAsync(() -> {
            a = "aa";
        }).thenRun(() -> {
            System.out.println(a);
        });
        CompletableFuture.runAsync(() -> {
            String aa ="1";
        }).thenAccept(resultA -> {
            System.out.println(resultA);
        });
        CompletableFuture.runAsync(() -> {
            c = "cc";
        }).thenApply(resultA -> {
            System.out.println(c);
            return "resultB";
        });

        CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
        });
        CompletableFuture.supplyAsync(() -> "resultA").thenAccept(resultA -> {
            System.out.println(resultA);
        });
        CompletableFuture.supplyAsync(() -> "resultA").thenApply(resultA -> resultA + " resultB");
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {

        }
    };

    Future fun = new Future() {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public Object get() throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    };
}
