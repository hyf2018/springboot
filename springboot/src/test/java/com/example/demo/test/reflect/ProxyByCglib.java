package com.example.demo.test.reflect;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/* 
 * CGLib 动态代理
	CGLib 动态代理相对于 JDK 动态代理局限性就小了很多，目标对象不需要实现接口，
	底层是通过继承目标对象产生代理子对象。
	（代理子对象中继承了目标对象的方法，并可以对该方法进行增强）
*/
public class ProxyByCglib {
	public static void main(String[] args) {

		// CGLIB 动态代理
		CglibProxy proxy = new CglibProxy();
		Panda panda = (Panda) proxy.getInstance(new Panda());
		panda.eat();

	}

}

//====================== CGLIB 示例 ==========================

class Panda {
	public void eat() {
		System.out.println("The panda is eating");
	}
}

class CglibProxy implements MethodInterceptor {
	private Object target; // 代理对象

	public Object getInstance(Object target) {
		this.target = target;

		// 创建增强器
		Enhancer enhancer = new Enhancer();
		// 设置需要增强的类的对象
		enhancer.setSuperclass(this.target.getClass());
		// 设置回调方法
		enhancer.setCallback(this);
		// 创建代理对象
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("调用前");
		Object result = methodProxy.invokeSuper(o, objects); // 执行方法调用
		System.out.println("调用后");
		return result;
	}
}
