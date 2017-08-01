package com.wuxi.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.Test;

public class PathTest {

	@Test
	public void path(){
		//当前工程根路径
		System.out.println(Paths.get(".").toAbsolutePath().normalize());
		//当前类路径
		System.out.println(this.getClass().getClassLoader().getResource(".").getPath());
		//当前类的父路径
		System.out.println(this.getClass().getResource(".").getPath());
		//相对路径
		Path protect = Paths.get("/Users/dasouche/Documents/project/chunlei");
		Path listing = protect.resolve("target/test-classes/com/wuxi/service/ClassLoadTest.class");
		System.out.println(listing.getFileName());
		//名称元素数量
		System.out.println(listing.getNameCount());
		System.out.println(listing.getParent());
		System.out.println(listing.getRoot());
		System.out.println(listing.toFile().exists());
		
		Path download = Paths.get("/Users/dasouche/Downloads");
		//相对路径
		System.out.println(download.relativize(protect));
		System.out.println(protect.relativize(download));
		System.out.println(download.startsWith("/Users/dasouche"));
		System.out.println(download.endsWith("Downloads"));
	}
	
	@Test
	public void files() throws IOException{
		Path zip = Paths.get("/Users/dasouche/Downloads/XX-Net-3.3.1.zip");
		System.out.println(Files.getLastModifiedTime(zip));
		System.out.println(Files.size(zip));
		System.out.println(Files.isSymbolicLink(zip));
		System.out.println(Files.isDirectory(zip));
		System.out.println(Files.readAttributes(zip, "*"));
	}
	
	@Test
	public void fliter(){
		Path dir = Paths.get("/Users/dasouche/Downloads");
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.jpg")){
			//遍历目录
			for(Path entry : stream){
				System.out.println(entry.getFileName());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			//遍历目录树(目录及子目录)
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					   throws IOException{
				     if(file.toString().endsWith(".js")){
				    	 System.out.println(file.toAbsolutePath());
				     }
			        return FileVisitResult.CONTINUE;
			    }
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void directory(){
		//java8 方法引用
		File[] files = new File("/Users/dasouche/Downloads").listFiles(File::isFile);
		for(File f : files){
			System.out.println(f.getName());
		}
	}
	
	
}
