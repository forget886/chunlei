package com.wuxi.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import com.wuxi.bean.vo.User;

public class ClassLoadTest {

	@Test
	public void load(){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("current loader: " + loader);
		System.out.println("parent loader: " + loader.getParent());
		System.out.println("grandparent loader: " + loader.getParent().getParent());
	}
	
	@Test
	public void reflect(){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			Class<?> clazz = loader.loadClass("com.wuxi.bean.vo.User");
			User user = (User) clazz.newInstance();
			
			Method setName = clazz.getMethod("setName", String.class);
			setName.invoke(user, "zhangsan");
			
			Method setSchool = clazz.getMethod("setSchool", String.class);
			setSchool.invoke(user, "外国语");
			
			Method setWorkDay = clazz.getSuperclass().getDeclaredMethod("setWorkDay", Date.class);
			setWorkDay.setAccessible(true);
			setWorkDay.invoke(user, new Date());//赋值不进去？
			
			/*
			 * 1、getMethod是拿到本类所有public方法（包括继承）
			 * 2、getDeclaredMethod是拿到所有private protect public方法（不包括继承）
			 * 3、要想拿到父类的private、protected方法,必须用getSuperclass().getDeclaredMethod
			 * 4、method的invoke方法只能调用public方法，private、protected方法会报IllegalAccessException,要setAccess(true)
			 */
			System.out.println(user);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
	}
}
