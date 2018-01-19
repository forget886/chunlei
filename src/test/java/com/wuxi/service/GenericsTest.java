package com.wuxi.service;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

	public static void main(String[] args) {
		MyNode mn = new MyNode(5);
		Node<Integer> node = mn;
		node.setData(6);
		
		List<String> list = new ArrayList<String>();
		//append(list, String.class);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	//泛型方法：在方法返回类型之前加类型参数申明部分<>
	public static <T> void append(List<T> list,Class<T> clazz){
		try {
			T t = clazz.newInstance();
			list.add(t);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}

//泛型类：在类名后面添加类型参数声明部分
class Node<T>{
	public T data;
	public Node(T data){
		this.data = data;
	}
	public void setData(T data){
		System.out.println(data);
		this.data = data;
	}
	
}

class MyNode extends Node<Integer>{
	public MyNode(Integer data) {
		super(data);
	}
	public void setData(Integer data){
		System.out.println(data);
		this.data = data;
	}
	
}