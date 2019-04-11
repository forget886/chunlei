package com.wuxi.service;

public class ArrayListTest {

    public static void main(String[] args) {
        int oldCapacity = 16;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println(newCapacity);
    }
}
