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

import org.apache.ibatis.javassist.compiler.ast.NewExpr;
import org.junit.Test;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.wuxi.aop.aspect.HelloWorld;
import com.wuxi.aop.aspect.HelloWorldImpl;

public class CommonTest {

	@Test
	public void relative(){
		System.out.println(StringUtils.applyRelativePath("/ff/cc","aa/bb"));
	}
	
	public static class SleepInterrupt extends Object implements Runnable{  
        public void run(){  
            try{  
                System.out.println("thread-sleep for 2000 seconds"); 

                Thread.sleep(2000000);  
                System.out.println("thread -waked up");  
            }catch(InterruptedException e){  
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
        }catch(InterruptedException e){  
            e.printStackTrace();  
        }  
        System.out.println("main() - interrupting other thread");  
        //中断线程t  
        t.interrupt();  

        System.out.println("main() - leaving");  

    }

    @Test
    public void getIp(){
    	try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }

	@Test
	public void dateTest(){
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
		        if(f.isFile())
		            System.out.println(" 是一个文件");
		        else if(f.isDirectory())
		            System.out.println(" 是一个目录");
 	}
	
	//{"bizExt":{"url":"/postsuccess/30301133085133/?source=car"},"bizCode":"redirect"}
	private String getThirdId(String url){
		int start = url.indexOf("success/");
		int end = url.indexOf("/?s");
		return url.substring(start+"success/".length(), end);
	}
	
	@Test
	public void nulllTest(){
		Map<String, String> map = new HashMap<>();
		map.put(null, "1");
		map.put(null,"2");
		System.out.println(map.get(null));
		
		Set<String> set = new HashSet<>();
		set.add("1");
		set.add(null);
		System.out.println(set);
	}
	
	@Test
	public void urlDecode(){
		String url = "%7B%22buytime%22%3A%222010%22%2C%22picdesc16%22%3A%22%22%2C%22cateapplyed%22%3A%2229%22%2C%22userid%22%3A%2240297190288656%22%2C%22fcookie%22%3A%223eaee5c0-615d-4b99-8532-2ac1a9e6000f%22%2C%22chengshipz%22%3A%22%22%2C%22yzm%22%3A%22%22%2C%22type%22%3A%220%22%2C%22yczbpic%22%3A%22%22%2C%22gouchefapiao%22%3A%22%22%2C%22installment%22%3A%22678612%22%2C%22shangshishijian%22%3A%22%22%2C%22tingshoushijian%22%3A%22%22%2C%22chelingqj%22%3A%222009_2012%22%2C%22guchejia%22%3A%22%22%2C%22vin%22%3A%22WMWMM3107ATN72847%22%2C%22Pic%22%3A%22%2Fp1%2Fbig%2Fn_v21be10fc9735848b6a63168573f7eb889.jpg%7C%2Fp1%2Fbig%2Fn_v2ffc3641bbdad43bbaad192827b4aaa21.jpg%7C%2Fp1%2Fbig%2Fn_v27a4ad49bc587442080ee180c448d05ae.jpg%7C%2Fp1%2Fbig%2Fn_v26a438519004e47abadaf838f1d7a1a43.jpg%7C%2Fp1%2Fbig%2Fn_v21e098bb2aa6248d596c2174fc140cf7a.jpg%7C%2Fp1%2Fbig%2Fn_v227eeda26bc7048bd878ff09fe39f0f9a.jpg%7C%2Fp1%2Fbig%2Fn_v23c1ce1ee91ea4935872f84a95000b4a8.jpg%7C%2Fp1%2Fbig%2Fn_v2f018926c5016433886f78a89f6c7c079.jpg%7C%2Fp1%2Fbig%2Fn_v28a409d99b32c4acbb944ed4f6e0bd28b.jpg%7C%2Fp1%2Fbig%2Fn_v282c1a33840de4c588c6deca2e422da57.jpg%7C%2Fp1%2Fbig%2Fn_v294a34573124e49229d9dea3fd4a8787c.jpg%7C%7C%2Fp1%2Fbig%2Fn_v225c5c41efd704c008395e4880b685b5a.jpg%7C%2Fp1%2Fbig%2Fn_v28cfeb506c87b4630aa7e3d0432e1a074.jpg%7C%2Fp1%2Fbig%2Fn_v2f1a099c2ddc741f8b51ed02b907c8f9b.jpg%7C%2Fp1%2Fbig%2Fn_v20e377fca353643aaa5b0573661fa8a00.jpg%22%2C%22shangpaiyuefen%22%3A%22515686%22%2C%22installmentbak%22%3A%220%22%2C%22MinPrice%22%3A%2216.68%22%2C%22captcha_type%22%3A%22%22%2C%22vinstate%22%3A%22%22%2C%22gobquzhi%22%3A%22brand%5Cu003dMINI%5Cu0026chexi%5Cu003dCLUBMAN%5Cu0026carchexing%5Cu003d2008%25E6%25AC%25BE1.6T%25E6%2589%258B%25E5%258A%25A8%5Cu0026cheshenyanse%5Cu003d%25E5%2585%25B6%25E4%25BB%2596%5Cu0026chelingqj%5Cu003d5-8%25E5%25B9%25B4%5Cu0026buytime%5Cu003d2010%25E5%25B9%25B4%5Cu0026shangpaiyuefen%5Cu003d6%25E6%259C%2588%5Cu0026rundistance%5Cu003d9.3%5Cu0026rundistanceqj%5Cu003d8-12%25E4%25B8%2587%25E5%2585%25AC%25E9%2587%258C%5Cu0026minprice%5Cu003d16.68%5Cu0026baoyang%5Cu003d%25E6%2598%25AF%5Cu0026cateapplyed%5Cu003d29%5Cu0026localapplyed%5Cu003d413%22%2C%22syshijian%22%3A%22%22%2C%22cjshijian%22%3A%22%22%2C%22MinPriceqj%22%3A%22%22%2C%22jzpz%22%3A%22%22%2C%22chapaihao%22%3A%22%22%2C%22erscpinpai%22%3A%22MINICLUBMAN%22%2C%22ghcs%22%3A%22%22%2C%22dengjizheng%22%3A%22%22%2C%22localapplyed%22%3A%22413%22%2C%22Phone%22%3A%2218664885108%22%2C%22cheshenyanse%22%3A%2214%22%2C%22rundistanceqj%22%3A%228_12%22%2C%22xbsx%22%3A%22%22%2C%22hidPostParam%22%3A%220%22%2C%22HiddenForPara%22%3A%22%22%2C%22shifouyishou%22%3A%220%22%2C%22shifoufufeifabu%22%3A%220%22%2C%22GTID%22%3A%22%22%2C%22guohufeiyong%22%3A%22%22%2C%22caraddress%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%E4%B8%9C%E8%8E%9E%22%2C%22chexi%22%3A%22416131%22%2C%22picdesc9%22%3A%22%22%2C%22picdesc8%22%3A%22%22%2C%22picdesc7%22%3A%22%22%2C%22picdesc6%22%3A%22%22%2C%22picdesc5%22%3A%22%22%2C%22picdesc4%22%3A%22%22%2C%22picdesc3%22%3A%22%22%2C%22picdesc2%22%3A%22%22%2C%22ywdy%22%3A%22%22%2C%22postparam_userid%22%3A%2240297190288656%22%2C%22picdesc1%22%3A%22%22%2C%22yczhibao%22%3A%22525381%22%2C%22Content%22%3A%222008%E6%AC%BE++MINI+CLUBMAN++1.6T+COOPER+S+%5Cn+%5Cn%22%2C%22cube_post_jsonkey%22%3A%22%7B%5C%22jzJson%5C%22%3Anull%2C%5C%22znJson%5C%22%3Anull%2C%5C%22zdJson%5C%22%3A%7B%5C%22infotop_time_price_string%5C%22%3A%5C%2224%2C49.92%3B72%2C146.76%2C149.76%3B168%2C331.97%2C349.44%3B360%2C688.90%2C748.80%3B720%2C1347.84%2C1497.60%5C%22%2C%5C%22infotop_hourconsume%5C%22%3A%5C%222.08%5C%22%2C%5C%22infotop_cateId%5C%22%3A%5C%2229%5C%22%2C%5C%22infotop_localId%5C%22%3A%5C%22413%5C%22%2C%5C%22infotop_categoryId%5C%22%3A%5C%220%5C%22%2C%5C%22infotop_cityId%5C%22%3A%5C%22413%5C%22%2C%5C%22infotop_userId%5C%22%3A%5C%2240297190288656%5C%22%2C%5C%22infotop_source%5C%22%3A%5C%221501%5C%22%2C%5C%22infotop_hotCoafficient%5C%22%3A%5C%221.5%5C%22%2C%5C%22selectTime%5C%22%3A%5C%22%5C%22%2C%5C%22allConsume%5C%22%3A%5C%220.00%5C%22%2C%5C%22couponPassword%5C%22%3A%5C%22%5C%22%2C%5C%22couponAmount%5C%22%3A%5C%220.00%5C%22%2C%5C%22selectEventId%5C%22%3A%5C%22%5C%22%2C%5C%22topType%5C%22%3A%5C%220%5C%22%2C%5C%22selectAutoTopVal%5C%22%3A%5C%221%5C%22%2C%5C%22canMakeInfoTop%5C%22%3A%5C%22true%5C%22%7D%2C%5C%22jzFee%5C%22%3A%5C%220%5C%22%2C%5C%22znFee%5C%22%3A%5C%220%5C%22%2C%5C%22zdFee%5C%22%3A%5C%220.00%5C%22%2C%5C%22jzCop%5C%22%3A%5C%220%5C%22%2C%5C%22znCop%5C%22%3A%5C%220%5C%22%2C%5C%22zdCop%5C%22%3A%5C%220.00%5C%22%2C%5C%22sourceId%5C%22%3A%5C%221501%5C%22%2C%5C%22productId%5C%22%3A%5C%22100002%5C%22%2C%5C%22secondCateId%5C%22%3A%5C%2229%5C%22%2C%5C%22dispSecondCateId%5C%22%3A%5C%2229%5C%22%2C%5C%22cityId%5C%22%3A%5C%22413%5C%22%2C%5C%22dispCityId%5C%22%3A%5C%22413%5C%22%7D%22%2C%22chapaihao1%22%3A%22%22%2C%22escpcLocal%22%3A%22post.58%22%2C%22yczbpic1%22%3A%22%22%2C%22pinpai%22%3A%22%22%2C%22shengfenpz%22%3A%22%22%2C%22kucheid%22%3A%22%22%2C%22Title%22%3A%22MINI+CLUBMAN+2008%E6%AC%BE1.6T%E6%89%8B%E5%8A%A8%22%2C%22qxshijian%22%3A%22%22%2C%22picdesc12%22%3A%22%22%2C%22picdesc13%22%3A%22%22%2C%22picdesc14%22%3A%22%22%2C%22picdesc15%22%3A%22%22%2C%22captcha_input%22%3A%22%22%2C%22picdesc10%22%3A%22%22%2C%22carchexing%22%3A%22811935%22%2C%22picdesc11%22%3A%22%22%2C%22pictag%22%3A%22%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%7C%22%2C%22brand%22%3A%22416130%22%2C%22isBiz%22%3A%221%22%2C%22gobalsokey%22%3A%22MINI%7CCLUBMAN%7C2008%E6%AC%BE1.6T%E6%89%8B%E5%8A%A8%7C8-12%E4%B8%87%E5%85%AC%E9%87%8C%7C5-8%E5%B9%B4%7C2010%7C6%7C%E6%98%AF%7C%E5%85%B6%E4%BB%96%7C9.3%7C16.68%22%2C%22rundistance%22%3A%229.3%22%2C%22shangpainianyue%22%3A%22201006%22%2C%22baoyang%22%3A%22515673%22%2C%22goblianxiren%22%3A%22%E9%99%88%E8%82%B2%E9%BE%99%22%7D";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
	}
	
	@Test
	public void convert(){
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
		for(int i=0;i<100;i++){
			System.out.println(nextUniqueNumber.getAndIncrement());
		}
	}
	
	@Test
	public void addQueue(){
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
	public void generateHashcode(){
		System.out.println(ObjectUtils.nullSafeHashCode(new com.wuxi.bean.vo.Car()));
		System.out.println(ObjectUtils.nullSafeHashCode(new com.wuxi.bean.vo.Car()));
	}
	
	@Test
	public void charIterator(){
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
	public void assgin(){
		System.out.println(HelloWorld.class.isAssignableFrom(HelloWorldImpl.class));
		System.out.println(HelloWorldImpl.class.isAssignableFrom(HelloWorldImpl.class));
		System.out.println(HelloWorldImpl.class.isAssignableFrom(HelloWorld.class));
	}
	
	@Test
	public void box(){
		int a = 500;
		Integer b = a + 0;
		//数值运算一律拆箱
		System.out.println(a == b);
	}
	
	@Test
	public void con(){
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		System.out.println(map.putIfAbsent("a", 1));
		System.out.println(map.putIfAbsent("a", 2));
		System.out.println(map.putIfAbsent("a", 3));
	}
	
	@Test
	public void time(){
		System.out.println(TimeUnit.MINUTES.toMillis(1));
		System.out.println(System.currentTimeMillis());
	}
	
}
