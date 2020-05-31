package com.example.demo.gc;

public class GcTest {
	public static void main(String[] args) {
		// 为了能够看完整日志，等20s
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 测试对象在新生代N岁时进入老年代
//		String[] id = { "Test_MaxTenuringThreshold" };
		// 测试minorGC时候的GC情况
//        String[] id = {"Test_NewSize"};
		// 测试majorGC时候的GC情况
        String[] id = {"Test_OldSize"};

		// 开始测试
		if (id[0].equals("Test_MaxTenuringThreshold")) {
			// 观察MaxTenuringThreshold=3时候的GC情况
			ThreadTest threadTest = new ThreadTest(0);
			new Thread(threadTest).start();
			while (true) {
				threadTest = new ThreadTest(1);
				new Thread(threadTest).start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (id[0].equals("Test_NewSize")) {
			// 观察minorGC时候的GC情况
			while (true) {
				ThreadTest threadTest = new ThreadTest(1);
				new Thread(threadTest).start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (id[0].equals("Test_OldSize")) {
			// 观察majorGC时候的GC情况
			while (true) {
				ThreadTest threadTest = new ThreadTest(2);
				new Thread(threadTest).start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
