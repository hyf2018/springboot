package com.example.demo.test.reflect;

import java.lang.reflect.Method;

public class ReflectTest {
	public static void main(String[] args) {
		try {
			/*
			 * // 获取String所对应的Class对象 Class<?> c = String.class; // 获取String类带一个String参数的构造器
			 * Constructor constructor; constructor = c.getConstructor(String.class); //
			 * 根据构造器创建实例 Object obj = constructor.newInstance("23333");
			 * System.out.println(obj);
			 * 
			 * //判断是否为某个类的实例 boolean isInstance = c.isInstance(String.class);
			 * 
			 */

//			获取方法
			Class<?> c = TestClass.class;
			Method[] methods = c.getMethods();
			Method[] declaredMethods = c.getDeclaredMethods();
			// getMethods()方法获取的所有方法
			System.out.println("getMethods获取的方法：");
			for (Method m : methods)
				System.out.println(m);
			// getDeclaredMethods()方法获取的所有方法
			System.out.println("getDeclaredMethods获取的方法：");
			for (Method m : declaredMethods)
				System.out.println(m);

			// 获取methodClass类的add方法
			Method method = c.getMethod("add", int.class, int.class);
			// 使用Class对象的newInstance()方法来创建Class对象对应类的实例。
			Object obj = c.newInstance();
			// 调用method对应的方法 => add(1,4)
			Object result = method.invoke(obj, 1, 4);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
