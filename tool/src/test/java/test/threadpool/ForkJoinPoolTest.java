package test.threadpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author ssl
 * @create 2018/08/23 16:20
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new Runnable() {
            @Override
            public void run() {
                new RecursiveAction() {
                    @Override
                    protected void compute() {
                        for (int i = 0; i < 10; i++) {
                            System.out.println(Thread.currentThread().getName() + ":" + i);
                        }
                    }
                }.fork();
                new RecursiveAction() {
                    @Override
                    protected void compute() {
                        for (int i = 10; i < 20; i++) {
                            System.out.println(Thread.currentThread().getName() + ":" + i);
                        }
                    }
                }.fork();
            }
        });
        // 等待10秒
        forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
