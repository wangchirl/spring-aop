package com.shadow.aop.xml.service;

/**
 * @author shadow
 * @create 2020-08-17
 * @description
 */
public class UserServiceImpl implements UserService {
	@Override
	public void hello() {
		System.out.println("hello invoked~");
	}
}
