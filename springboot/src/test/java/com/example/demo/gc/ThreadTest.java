package com.example.demo.gc;

public class ThreadTest implements Runnable {

    int num;

    public ThreadTest(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("This is Thread " + num);

        // num=0的时候测试对象在新生代3岁时进入老年代
        if(num == 0) {
            // 新生代128M(-Xmn128m，eden=102.4M，To_Survivor=12.8M，From_Survivor=12.8M)
            // ~7M(8*1000000+16+8+padding)
            // 在64位的系统中, 数组占用内存为: 型别占用内存 * 数组长度 + 16（object overhead占用16bytes）+ 8（数组长度）+ padding
            long[] a = new long[1000000];
            while (true) {
                try {
                    System.out.println("This is Thread " + num + "，Thread is " + this.toString() + "，Array length is " + a.length);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if(num == 1) {
            // 新生代128M(-Xmn128m，eden=102.4M，To_Survivor=12.8M，From_Survivor=12.8M)
            // ~15M(8*2000000+16+8+padding)
            // 在64位的系统中, 数组占用内存为: 型别占用内存 * 数组长度 + 16（object overhead占用16bytes）+ 8（数组长度）+ padding
            long[] a = new long[2000000];
            System.out.println("This is Thread " + num + "，Thread is " + this.toString() + "，Array length is " + a.length);
        } else if(num == 2) {
            // 老年代128M(-Xms256m -Xmx256m，OldSize=256-128)
            // ~76M(8*10000000+16+8+padding)
            // 在64位的系统中, 数组占用内存为: 型别占用内存 * 数组长度 + 16（object overhead占用16bytes）+ 8（数组长度）+ padding
            long[] a = new long[10000000];
            System.out.println("This is Thread " + num + "，Thread is " + this.toString() + "，Array length is " + a.length);
        }
    }
}