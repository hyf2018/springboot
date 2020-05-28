package com.example.demo.test.Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadTest {
	public static void main(String[] args) throws Exception {
		/*
		 * FutureTask用来表示可获取结果的异步任务 通过get()方法来获取异步任务的结果，但是会阻塞当前线程直至异步任务执行结束。
		 * 一旦任务执行结束，任务不能重新启动或取消。
		 * FutureTask即便调用了多次，也只会执行一次run方法（任务只执行一次），（尤其在高并发的情况下，确保只执行一次）
		 */
//	    MyCallable mc = new MyCallable();
//	    FutureTask<Integer> ft = new FutureTask<>(mc);
//	    Thread thread = new Thread(ft);
//	    thread.start();
//	    System.out.println(ft.get());

		/*
		 * Executor Executor管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。
		 * 这里的异步是指多个任务的执行互不干扰，不需要进行同步操作。
		 * 
		 * 主要有三种 Executor：
		 * 
		 * CachedThreadPool：在可用时将重新使用以前构造的线程； 
		 * FixedThreadPool：所有任务只能使用固定大小的线程；
		 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
		 */
//	    ExecutorService executorService = Executors.newFixedThreadPool(10);
	    ExecutorService executorService = Executors.newCachedThreadPool();
	    MyRunnable myRunnable = new MyRunnable();
    	MyCallable mc = new MyCallable();
	    FutureTask<Integer> ft = new FutureTask<>(mc);
	    for (int i = 0; i < 5; i++) {
	    	//ft只会执行一次，myRunnable会执行5次
	        executorService.execute(ft);
//	        executorService.execute(myRunnable);
	    }
	    executorService.shutdown();

		/*
		 * Executor 的中断操作 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是
		 * shutdownNow() 方法， 则相当于调用每个线程的 interrupt() 方法。
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
