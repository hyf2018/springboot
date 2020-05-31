package com.example.demo.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * Executors 线程池
*/
public class ExecutorsTest {
	public static void main(String[] args) throws Exception {
		/* 线程池7大参数
		ThreadPoolExecutor(
				int corePoolSize, 
				int maximumPoolSize, 
				long keepAliveTime, 
				TimeUnit unit, 
				BlockingQueue<Runnable> workQueue, 
				ThreadFactory threadFactory, 
				RejectedExecutionHandler handler) */
//		new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, 
//				unit, workQueue, threadFactory, handler)
		
		/*
		 * Executor 
		 * Executor管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。
		 * 这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
		 * 
		 * 主要有四种 Executor：
		 * CachedThreadPool：在可用时将重新使用以前构造的线程； 
		 * FixedThreadPool：所有任务只能使用固定大小的线程；
		 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool；
		 * ScheduledThreadPool：调度命令在给定的延迟之后运行，或定期执行：
		 */
//	    ExecutorService executorService = Executors.newFixedThreadPool(10);
		ExecutorService executorService = Executors.newCachedThreadPool();
		MyRunnable myRunnable = new MyRunnable();
		MyCallable mc = new MyCallable();
		FutureTask<Integer> ft = new FutureTask<>(mc);
		for (int i = 0; i < 5; i++) {
			// FutureTask只会执行一次，myRunnable会执行5次
			executorService.execute(ft);
//	        executorService.execute(myRunnable);
		}
		executorService.shutdown();

		/*
		 * Executor 的中断操作 
		 * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
		 * 但是如果调用的是shutdownNow() 方法， 则相当于调用每个线程的 interrupt() 方法。
		 */
//	    ExecutorService executorService = Executors.newCachedThreadPool();
//	    executorService.execute(() -> {
//	        try {
//	            Thread.sleep(2000);
//	            System.out.println("Thread run");
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//	    });
//	    executorService.shutdownNow();
//	    System.out.println("Main run");

	}
}
