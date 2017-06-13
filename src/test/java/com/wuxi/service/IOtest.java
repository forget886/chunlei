package com.wuxi.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class IOtest {

	@Test
	public void read(){
		File file = new File("/Users/dasouche/Desktop/sync.txt");
		try {
			FileReader reader = new FileReader(file);
			StringBuilder tmp = new StringBuilder();
			char[] tmps = new char[1024];
			int len = -1;
			while((len = reader.read(tmps)) != -1){
				System.out.println(len);
				tmp.append(new String(tmps,0,len));
			}
			
			System.out.println(tmp.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
