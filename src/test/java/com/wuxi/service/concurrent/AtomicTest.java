package com.wuxi.service.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

	public static void main(String[] args) {
		AtomicInteger integer = new AtomicInteger(12);
		System.out.println(integer.incrementAndGet());
		System.out.println(integer.getAndIncrement());
		System.out.println(integer);
		integer = null;
		System.gc();
	}
}
