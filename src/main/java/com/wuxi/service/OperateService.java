package com.wuxi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wuxi.aop.aspect.HelloWorld;
import com.wuxi.event.MailSend;


public class OperateService implements BeanNameAware{
	
	private static final Logger logger = LoggerFactory.getLogger(OperateService.class);
	
	private  UserService userService;
	@Autowired
	private MailSend mailSend;
	
	private String beanName;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public OperateService(String name){
		System.out.println(name);
	}

	public static void main(String[] args) {
		AbstractApplicationContext father = new ClassPathXmlApplicationContext("applicationContext.xml");
		//父子容器 子容器要刷新才能获取bean
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(father);
		context.refresh();
		
		//是否允许循环依赖
		((ClassPathXmlApplicationContext)context).setAllowCircularReferences(false);
//		Waiter waiter = (Waiter) context.getBean("waiterProxy");
//		waiter.greetTo("xixi");
		HelloWorld helloWorld = (HelloWorld) context.getBean("hello");
		helloWorld.hello();
		helloWorld.bye("xiao ma");
		
//		Factory factory = (Factory) context.getBean("factory");
//		logger.info(factory.getArea().getLocation());
//		OperateService operateService = (OperateService) context.getBean("operate");
//		System.out.println(operateService.getUserService().getUser("李强"));
		//获取spring xml配置bean的装配信息
//		BeanDefinition operation = father.getBeanFactory().getBeanDefinition("car2");
//		for(PropertyValue pv:operation.getPropertyValues().getPropertyValues()){
//			System.out.println("属性对：" + pv.getName()  + " : " + pv.getValue());
//		}
		//扫描包下的类
//		try {
//			Resource[] resources = context.getResources("classpath*:com/wuxi/dao/**/*.class");
//			for(Resource r: resources){
//				logger.info("类：" + r.getURL().getPath());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		operateService.getMailSend().sendMail("zz");
//		Resource resource = context.getResource(".");
//		try {
//			logger.info(resource.getURL().toString());//返回类路径
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		Car car = (Car) context.getBean("car2");
//		logger.info(car.getClass().getName());
//		CarFactoryBean carFactoryBean = (CarFactoryBean) context.getBean("&car2");
//		logger.info(carFactoryBean.getClass().getName());
//		logger.info(operateService.getUserService().getUser("李强"));
//		ListableBeanFactory factory = context;
//		logger.info("bean count:{}",factory.getBeanDefinitionCount());
//		for(String name : factory.getBeanDefinitionNames()){
//			logger.info(name);
//		}
		//context只有getResources支持Ant风格的路径
		//logger.info(context.getResource("classpath:logba*.xml").exists()+"");
//		Resource[] resources = null;
//		try {
//			resources = context.getResources("classpath:logba*.xml");
//		} catch (IOException e) {
//		}
//		for(Resource r:resources){
//			logger.info(r.getDescription()+"");
//		}
//		Car car = context.getBean(Car.class);
//		System.out.println(car);
		context.destroy();
		father.destroy();
	}
	
	public MailSend getMailSend(){
		return this.mailSend;
	}

	public UserService getUserService() {
		return userService;
	}

	@Override
	public void setBeanName(String name) {
		beanName = name;
	}
	
	public String getBeanName(){
		return beanName;
	}

}
