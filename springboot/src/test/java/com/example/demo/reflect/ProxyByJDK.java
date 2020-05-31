package com.example.demo.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * JDK动态代理
	目标对象必须实现接口
*/
public class ProxyByJDK {

	public static void main(String[] args) {

		// JDK 动态代理
		AnimalProxy proxy = new AnimalProxy();
		Animal dogProxy = (Animal) proxy.getInstance(new Dog());
		dogProxy.eat();

	}

}

interface Animal {
	void eat();
}

class Dog implements Animal {
	@Override
	public void eat() {
		System.out.println("The dog is eating");
	}
}

class Cat implements Animal {
	@Override
	public void eat() {
		System.out.println("The cat is eating");
	}
}

// JDK 代理类
class AnimalProxy implements InvocationHandler {
	private Object target; // 代理对象

	public Object getInstance(Object target) {
		this.target = target;
		/*
		      取得代理对象
			 这里的 Proxy 是 JDK 中的 api，newProxyInstance() 方法中的参数如下：
				 第一个参数：目标对象的类加载器 ； 
				 第二个参数：目标对象的接口  ； 
				 第三个参数：代理对象的执行处理器 ； 
		 */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("调用前");
		Object result = method.invoke(target, args); // 方法调用
		System.out.println("调用后");
		return result;
	}
}

// JDK 反例 extends 不能代理示例
//class Animal {
//    void eat(){
//        System.out.println("The animal is eating");
//    }
//}
//
//class Dog extends Animal {
//    @Override
//    public void eat() {
//        System.out.println("The dog is eating");
//    }
//}
//
//class Cat extends Animal {
//    @Override
//    public void eat() {
//        System.out.println("The cat is eating");
//    }
//}
