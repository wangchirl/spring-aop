package com.shadow.aop;

import com.shadow.aop.xml.service.UserService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author shadow
 * @create 2020-08-17
 * @description
 */
public class XMLAopApplication {

	public static void main(String[] args) {

		Resource resource = new ClassPathResource("spring-context.xml");

		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

		BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

		reader.loadBeanDefinitions(resource);

		UserService userService = (UserService) beanFactory.getBean("myAop");

		System.out.println(userService.getClass().getSuperclass()); // java.lang.reflect.Proxy

		/**
		 * com.shadow.aop.xml.service.UserService
		 * org.springframework.aop.SpringProxy
		 * org.springframework.aop.framework.Advised
		 * org.springframework.core.DecoratingProxy
		 */
		for (Class<?> aClass : userService.getClass().getInterfaces()) {
			System.out.println(aClass.getName());
		}

		userService.hello();
	}
}
