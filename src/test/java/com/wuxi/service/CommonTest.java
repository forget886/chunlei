package com.wuxi.service;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wuxi.aop.aspect.HelloWorld;
import com.wuxi.aop.aspect.HelloWorldImpl;

public class CommonTest {

    @Test
    public void relative() {
        System.out.println(StringUtils.applyRelativePath("/ff/cc", "aa/bb"));
    }

    public static class SleepInterrupt extends Object implements Runnable {
        public void run() {
            try {
                System.out.println("thread-sleep for 2000 seconds");

                Thread.sleep(2000000);
                System.out.println("thread -waked up");
            } catch (InterruptedException e) {
                System.out.println("thread-interrupted while sleeping");

                return;
            }
            System.out.println("thread-leaving normally");
        }
    }

    public static void main(String[] args) {

        SleepInterrupt si = new SleepInterrupt();
        Thread t = new Thread(si);
        t.start();

        //主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间  
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main() - interrupting other thread");
        //中断线程t  
        t.interrupt();

        System.out.println("main() - leaving");

    }

    @Test
    public void getIp() {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dateTest() {
        File f = new File("src");
        System.out.println(
                " 绝对路径：" + f.getAbsolutePath() +
                        "\n 可读：" + f.canRead() +
                        "\n 可写：" + f.canWrite() +
                        "\n 文件名：" + f.getName() +
                        "\n 上级目录：" + f.getParent() +
                        "\n 相对地址：" + f.getPath() +
                        "\n 长度：" + f.length() +
                        "\n 最近修改时间：" + f.lastModified()
        );
        if (f.isFile())
            System.out.println(" 是一个文件");
        else if (f.isDirectory())
            System.out.println(" 是一个目录");
    }

    //{"bizExt":{"url":"/postsuccess/30301133085133/?source=car"},"bizCode":"redirect"}
    private String getThirdId(String url) {
        int start = url.indexOf("success/");
        int end = url.indexOf("/?s");
        return url.substring(start + "success/".length(), end);
    }

    @Test
    public void nulllTest() {
        Map<String, String> map = new HashMap<>();
        map.put(null, "1");
        map.put(null, "2");
        System.out.println(map.get(null));

        Set<String> set = new HashSet<>();
        set.add("1");
        set.add(null);
        System.out.println(set);
    }

    @Test
    public void urlDecode() {
        String url = "consumer%3A%2F%2F172.17.40.191%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D16514%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1521618540050, consumer%3A%2F%2F172.17.41.14%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.5.9%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D5089%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1519724573900, consumer%3A%2F%2F172.17.41.22%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D8734%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1522131480809, consumer%3A%2F%2F172.17.11.234%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D3547%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1522655893209, consumer%3A%2F%2F172.17.41.10%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D20973%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1522314377769, consumer%3A%2F%2F172.17.40.226%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.5.9%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D6522%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1517971451508, consumer%3A%2F%2F172.17.41.10%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D14747%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1522658553804, consumer%3A%2F%2F172.17.40.226%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D1636%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1522657299236, consumer%3A%2F%2F172.17.40.226%2Fcom.souche.marketing.service.describe.ImportantConfigService%3Fapplication%3Derp-web%26category%3Dconsumers%26check%3Dfalse%26default.timeout%3D10000%26dubbo%3D2.6.4%26interface%3Dcom.souche.marketing.service.describe.ImportantConfigService%26pid%3D14220%26revision%3D1.2.2%26side%3Dconsumer%26timestamp%3D1521796721665";
        try {
            url = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url);
    }

    @Test
    public void convert() {
//		Map<String, String> map = new HashMap<String, String>();
//		System.out.println(map.put("a", "1"));
//		System.out.println(map.put("a", "2"));
//		System.out.println(map.put("a", "3"));
//		System.out.println("----------------");
//		System.out.println(0000012);
//		System.out.println(Modifier.FINAL);
//		System.out.println(Modifier.PUBLIC | Modifier.FINAL);
//		System.out.println("----------------");
        AtomicLong nextUniqueNumber = new AtomicLong();
        for (int i = 0; i < 100; i++) {
            System.out.println(nextUniqueNumber.getAndIncrement());
        }
    }

    @Test
    public void addQueue() {
        LinkedNode<String> queue = new LinkedNode<>();
        queue.add("aa");
        queue.remove();
        queue.remove();
        queue.print();
        queue.add("aa");
        queue.add("ab");
        queue.add("aa");
        queue.add("bb");
        queue.remove();
        queue.print();
    }

    @Test
    public void generateHashcode() {
        System.out.println(ObjectUtils.nullSafeHashCode(new com.wuxi.bean.vo.Car()));
        System.out.println(ObjectUtils.nullSafeHashCode(new com.wuxi.bean.vo.Car()));
    }

    @Test
    public void charIterator() {
        String name = "sdfd233df44rdf33gf";
//		CharacterIterator  iterator = new StringCharacterIterator(name,0);
//		char c = iterator.first();
//		while(c != CharacterIterator.DONE){
//			if(Character.isDigit(c)){
//				System.out.println(c);
//			}
//			c = iterator.next();
//		}

        StringBuffer buffer = new StringBuffer(name);
        System.out.println(buffer.toString());
        buffer.setLength(0);

        System.out.println(buffer.toString().length());
    }

    @Test
    public void assgin() {
        System.out.println(HelloWorld.class.isAssignableFrom(HelloWorldImpl.class));
        System.out.println(HelloWorldImpl.class.isAssignableFrom(HelloWorldImpl.class));
        System.out.println(HelloWorldImpl.class.isAssignableFrom(HelloWorld.class));
    }

    @Test
    public void box() {
        int a = 500;
        Integer b = a + 0;
        //数值运算一律拆箱
        System.out.println(a == b);
    }

    @Test
    public void con() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        System.out.println(map.putIfAbsent("a", 1));
        System.out.println(map.putIfAbsent("a", 2));
        System.out.println(map.putIfAbsent("a", 3));
    }

    @Test
    public void time() {
        System.out.println(TimeUnit.MINUTES.toMillis(1));
        System.out.println(System.currentTimeMillis());
    }

}
