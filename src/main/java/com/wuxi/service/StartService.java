package com.wuxi.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wuxi.bean.vo.Car;

public class StartService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		System.out.println(Arrays.toString(context.getAliases("operatea")));
//		System.out.println(context.getBean("operatea") == null);
		Car car11 = (Car) context.getBean("car");
		Car car12 = (Car) context.getBean("car");
		System.out.println(car11 == car12);
		Car car21 = (Car) context.getBean("car2");
		Car car22 = (Car) context.getBean("car2");
		System.out.println(car21 == car22);
//		System.out.println(operateService.getClass().getClassLoader().getResource("."));
//		System.out.println(operateService.getClass().getResource("."));
//		System.out.println(context.getBeanDefinitionCount());
//		for(String name : context.getBeanDefinitionNames()){
//			System.out.println(name);
//		}
//		Map<String, OperateService> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(context, OperateService.class, true, false);
//		System.out.println(map.size());
//		for(Entry<String, OperateService> entry:map.entrySet()){
//			System.out.println(entry.getKey());
//			System.out.println(entry.getValue().getBeanName());
//		}
	}
}
