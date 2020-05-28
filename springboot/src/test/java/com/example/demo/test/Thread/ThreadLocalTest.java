package com.example.demo.test.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 一、什么是ThreadLocal
ThreadLocal，可以称为线程本地存储。简单来说，就是ThreadLocal为共享变量在每个线程中都创建一个副本，每个线程可以访问自己内部的副本变量。
这样做的好处是可以保证共享变量在多线程环境下访问的线程安全性。

二、ThreadLocal的实际应用场景：
  1. 比如在线程级别，维护session,维护用户登录信息userID（登陆时插入，多个地方获取）
  2. 数据库的链接对象 Connection，可以通过ThreadLocal来做隔离避免线程安全问题

三、ThreadLocal的内存泄漏
  1、将ThreadLocal变量定义成private static的，这样的话ThreadLocal的生命周期就更长，由于一直存在ThreadLocal的强引用，
          所以ThreadLocal也就不会被回收，也就能保证任何时候都能根据ThreadLocal的弱引用访问到Entry的value值，然后remove它，防止内存泄露
  2、每次使用完ThreadLocal，都调用它的remove()方法，清除数据。
*/

public class ThreadLocalTest {
	// Atomic integer containing the next thread ID to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(100);

	// Thread local variable containing each thread's ID
	private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		}
	};

	// Returns the current thread's unique ID, assigning it if necessary
	public static int get() {
		return threadId.get();
	}

	public static void main(String[] args) {
		Thread[] threads = new Thread[5];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(() -> {
				int threadId = ThreadLocalTest.get();
				System.out.println(Thread.currentThread().getName() + " ; threadId:" + threadId);
			}, "Thread - " + i);
		}
		
		for (Thread t : threads) {
			t.start();
		}
	}
}