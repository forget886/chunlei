package com.wuxi.objectproxy;

public interface Performance{

	
	public void add(int id);
	
	default public void remove(int id){
		throw new UnsupportedOperationException();
	}
}
