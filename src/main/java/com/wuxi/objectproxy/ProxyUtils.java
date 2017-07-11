package com.wuxi.objectproxy;

import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;

import net.sf.cglib.proxy.Enhancer;
import sun.misc.ProxyGenerator;

public class ProxyUtils {

	/**
	 * 根据类信息将动态生成的二进制字节码保存在硬盘中
	 * @param clazz
	 * @param proxyName
	 */
	public static void generateClassFile(Class<?> clazz,String proxyName){
		byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
		String paths = clazz.getResource(".").getPath();//当前类的路径
		System.out.println(paths);
		System.out.println(clazz.getClassLoader().getResource(".").getPath());//当前类加载器的路径

		writeToFile(classFile, paths+proxyName+".class");
	}
	
	
	public static void generateCglibClassFile(Enhancer enhancer,String path,String proxyName){
		ClassWriter cw = new ClassWriter(0);
		try {
			enhancer.generateClass(cw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] classFile = cw.toByteArray();
		writeToFile(classFile, path+proxyName+".class");
	}
	
	public static void writeToFile(byte[] classFile,String name){
		System.out.println("class name:" + name);
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(name);
			out.write(classFile);
			out.flush();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
