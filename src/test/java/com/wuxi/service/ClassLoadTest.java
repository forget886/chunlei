package com.wuxi.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.sql.Driver;
import java.util.Date;
import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;
import org.springframework.context.support.AbstractRefreshableConfigApplicationContext;

import com.wuxi.bean.vo.User;

public class ClassLoadTest {

	@Test
	public void showInterface(){
		for(Class<?> intf : AbstractRefreshableConfigApplicationContext.class.getInterfaces()){
			System.out.println(intf.getName());
		}
	}
	
	@Test
	public void bootload() {
		URL[]  urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for(URL u:urls){
			System.out.println(u.getPath());
		}
		System.out.println("=================================");
		for(String s : System.getProperty("java.ext.dirs").split(":")){
			System.out.println(s);
		}
		System.out.println("=================================");
		for(String s : System.getProperty("java.class.path").split(":")){
			System.out.println(s);
		}
		
	}
	
	@Test
	public void move(){
		int count = Integer.SIZE-3;
		System.out.println(count);
		System.out.println((1 << count) - 1); 
		System.out.println((1 << count));
	}
	
	@Test
	public void load(){
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println("current loader: " + loader);
		System.out.println("parent loader: " + loader.getParent());
		System.out.println("grandparent loader: " + loader.getParent().getParent());
	}
	
	@Test
	public void contextClassLoader(){
//		//获取extclassloader
//	    ClassLoader extClassloader = ClassLoadTest.class.getClassLoader().getParent();
//	    System.out.println("extloader:"  +extClassloader);
//	    //设置当前线程上下文加载器为ext,而mysql的jar包是放到了classpath下，这样就加载不到mysql驱动类
//	    Thread.currentThread().setContextClassLoader(extClassloader);

		ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
		Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        //当父类加载器需要加载子类加载器中的资源时候可以通过设置和获取线程上下文类加载器来实现
        //默认是AppClassLoader
        System.out.println("current thread contextloader:"+Thread.currentThread().getContextClassLoader());
        System.out.println("current loader:" + ClassLoadTest.class.getClassLoader());
        System.out.println("ServiceLoader loader:" + ServiceLoader.class.getClassLoader());
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
			setWorkDay.invoke(user, new Date());
			System.out.println(user);
			
			Thread.sleep(1000);
			
			Field workDay = clazz.getSuperclass().getDeclaredField("workDay");
			System.out.println(workDay.getClass());
			System.out.println(workDay.getDeclaringClass());
			System.out.println(workDay.getModifiers());
			System.out.println(Modifier.isStatic(workDay.getModifiers()));
			workDay.setAccessible(true);
			workDay.set(user, new Date());
			
			/*
			 * 1、getMethod是拿到本类所有public方法（包括继承）
			 * 2、getDeclaredMethod是拿到所有private protect public方法（不包括继承）
			 * 3、要想拿到父类的private、protected方法,必须用getSuperclass().getDeclaredMethod
			 * 4、method的invoke方法只能调用public方法，private、protected方法会报IllegalAccessException,要setAccessible(true)
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
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		
	}
}

