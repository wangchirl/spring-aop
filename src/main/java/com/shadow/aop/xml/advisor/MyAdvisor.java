package com.shadow.aop.xml.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author shadow
 * @create 2020-08-17
 * @description
 */
public class MyAdvisor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("before invoke~");
		Object proceed = invocation.proceed();
		System.out.println("after invoke~");
		return proceed;
	}
}
