package test.threadpool;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：需求模拟：启动n个线程，往一个指定大小的队列里面放入数据
 *
 * @author ssl
 * @create 2018/08/23 15:28
 */
public class ThreadPool {

    /**
     * 存放数据的阻塞队列：设置队列存放最大数量为10
     */
    public static BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
    /**
     * 指定启动的线程数
     */
    public static final int threads = 5;

    /**
     *
     */
    @Test
    public void putQueue() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < threads; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            // queue.put(UUID.randomUUID().toString());//put：当队列已满时，后面线程将会阻塞。
                            queue.offer(UUID.randomUUID().toString());
                            //offer：当队列已满时，队列不会阻塞，后面线程会继续执行，但是会返回false，表示插入队列失败。
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " offer成功。queue size=" + queue.size());
                    }
                }
            });
        }
        // 等待10秒
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }


}
