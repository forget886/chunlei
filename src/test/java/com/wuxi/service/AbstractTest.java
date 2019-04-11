package com.wuxi.service;

import org.junit.Test;

public class AbstractTest {

    @Test
    public void get() {
        //加载类(实类、虚类)时即为静态变量分配好内存
        ContextHolder.a = 1;
        System.out.println(ContextHolder.get());
    }
}


abstract class ContextHolder {
    public static int a;

    public static int get() {
        return a;
    }
}