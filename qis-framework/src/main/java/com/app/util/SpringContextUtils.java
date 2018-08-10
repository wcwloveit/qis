package com.app.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware {
	
	/** Spring应用上下文环境  */
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtils.ctx = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return ctx.getBean(name);
	}

	/**
	 * 获取对象
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> requiredType) throws BeansException {
		return ctx.getBean(requiredType);
	}

	/**
	 * 获取类型为requiredType的对象
	 * 如果bean不能被类型转换，相应的异常将会被抛出（BeanNotOfRequiredTypeException）
	 * 
	 * @param name
	 *            bean注册名
	 * @param requiredType
	 *            返回对象类型
	 * @return Object 返回requiredType类型对象
	 * @throws BeansException
	 */
	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return ctx.getBean(name, requiredType);
	}

}
