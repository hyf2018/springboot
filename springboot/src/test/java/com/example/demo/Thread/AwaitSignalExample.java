package com.example.demo.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * await() signal() signalAll()
java.util.concurrent 类库中提供了 Condition 接口来实现线程之间的协调，
可以在 Condition 上调用 await() 方法使线程等待，其它线程调用 signal() 或 signalAll() 方法唤醒等待的线程。

相比于 wait() 这种等待方式，await() 可以指定等待的条件，因此更加灵活。

使用 Lock 来获取一个 Condition 对象。
*/
public class AwaitSignalExample {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void before() {
		lock.lock();
		try {
			System.out.println("before");
//            唤醒所有等待线程。 
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void after() {
		lock.lock();
		try {
//        	等待一直到当前线程发信号或 interrupted 。 
			condition.await();
			System.out.println("after");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
	    AwaitSignalExample example = new AwaitSignalExample();
	    executorService.execute(() -> example.after());
	    executorService.execute(() -> example.before());
	}
}
