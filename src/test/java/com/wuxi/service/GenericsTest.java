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