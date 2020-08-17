package com.shadow.aop;

import com.shadow.aop.statics.MyInterfaces;
import com.shadow.aop.statics.MyStaticProxy;
import com.shadow.aop.statics.MySubClass;

/**
 * @author shadow
 * @create 2020-08-17
 * @description
 *
 * 静态代理
 */
public class StaticProxyApplication {
	public static void main(String[] args) {

		MySubClass mySubClass = new MySubClass();
		MyInterfaces proxy = new MyStaticProxy(mySubClass);
		proxy.say();

	}
}
