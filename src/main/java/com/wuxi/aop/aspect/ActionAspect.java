package com.wuxi.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class ActionAspect implements Ordered {

    //Spring 的 AOP 代理由 Spring 的 IoC 容器负责生成、管理，其依赖关系也由 IoC 容器负责管理。
    @Pointcut("args(String,int)")
    public void cutArgs() {
    }

    ;

    @After("within(com.wuxi.aop..*.*Impl+)")
    public void record(JoinPoint joinpoint) {
        System.out.println("record");
        //业务类织入多个切面，按order决定织入顺序，生成一个代理类
        System.out.println("代理类： " + joinpoint.getThis().getClass().getName());
        System.out.println("拦截方法： " + joinpoint.getSignature().getName());
    }

    @After("@target(com.wuxi.aop.annotation.Monitorable)")
    public void record2(JoinPoint joinpoint) {
        System.out.println("record2");
        System.out.println("拦截方法： " + joinpoint.getSignature().getName());
    }

    @Before("cutArgs() and args(name,age)")
    public void record3(JoinPoint joinpoint, String name, int age) {
        System.out.println("record3 " + name + age);
        System.out.println("拦截方法： " + joinpoint.getSignature().getName());
    }

    /**
     * 当args参数入参为参数名时，先在增强方法中查到名称相同的入参并查到参数类型，这样就知道连接点方法的参数类型，
     * 然后将连接点的入参按顺序一一对应，再按照名称绑到增强方法的入参上
     *
     * @param joinpoint
     * @param name
     * @param age
     */
    @Around("args(name,age)")
    public void record5(ProceedingJoinPoint joinpoint, String name, int age) {
        System.out.println("record5 " + name + age);
        try {
            joinpoint.proceed(new Object[]{"换", 2});
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(value = "execution(* com.wuxi.aop.aspect.HelloWorldImpl.sell())", returning = "name")
    public void record4(JoinPoint joinpoint, String name) {
        System.out.println("record4 " + name);
        System.out.println("拦截方法： " + joinpoint.getSignature().getName());
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
