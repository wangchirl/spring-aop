package com.shadow.aop.statics;

/**
 * @author shadow
 * @create 2020-08-17
 * @description
 */
public class MyStaticProxy implements MyInterfaces {

	private MyInterfaces myInterfaces;

	public MyStaticProxy(MyInterfaces myInterfaces) {
		this.myInterfaces = myInterfaces;
	}

	@Override
	public void say() {
		System.out.println("before ~");
		myInterfaces.say();
		System.out.println("after ~");
	}
}
