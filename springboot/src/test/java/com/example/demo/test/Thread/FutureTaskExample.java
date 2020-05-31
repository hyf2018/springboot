package com.example.demo.test.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * FutureTask
它可以有返回值，返回值通过 Future 进行封装。FutureTask 实现了 RunnableFuture 接口，
该接口继承自 Runnable 和 Future 接口，这使得 FutureTask 既可以当做一个任务执行，也可以有返回值。
public class FutureTask<V> implements RunnableFuture<V>
public interface RunnableFuture<V> extends Runnable, Future<V>

FutureTask 可用于异步获取执行结果或取消执行任务的场景。当一个计算任务需要执行很长时间，那么就可以用 FutureTask 来封装这个任务，
主线程在完成自己的任务之后再去获取结果。
*/

public class FutureTaskExample {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
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

		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				int result = 0;
				for (int i = 0; i < 100; i++) {
					Thread.sleep(10);
					result += i;
				}
				return result;
			}
		});

		Thread computeThread = new Thread(futureTask);
		computeThread.start();

		Thread otherThread = new Thread(() -> {
			System.out.println("other task is running...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		otherThread.start();

		Integer result = futureTask.get();
		System.out.println(result);
	}
}