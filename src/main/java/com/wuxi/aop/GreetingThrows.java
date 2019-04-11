package com.wuxi.aop;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;

@Service("greetingThrows")
public class GreetingThrows implements ThrowsAdvice {

    /**
     * 注意到当异常发生时,Throw Advice 的任务只是执行对应的方法，您并不能在 Throw Advice 中将异常处理掉，在 Throw Advice 执行完毕后，
     * 原告的异常仍将传播至应用程序之中， Throw Advice 并不介入应用程序的异常处理，异常处理仍旧是应用程序本身所要负责的
     *
     * @param method
     * @param args
     * @param target
     * @param ex
     * @throws Throwable
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {
        System.out.println("抛出 " + ex.getMessage());
    }
}
