package com.wuxi.service;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.wuxi.util.CoreException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CommonTest {

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
		String url = "%7B%22rundistance%22%3A%222%22%2C%22gobalsokey%22%3A%22%E5%AE%9D%E9%A9%AC%7C5%E7%B3%BB%7C2017%E6%AC%BE535Li3.0%E8%87%AA%E5%8A%A8%E9%A2%86%E5%85%88%E5%9E%8B%7C1-3%E4%B8%87%E5%85%AC%E9%87%8C%7C1%E5%B9%B4%E4%BB%A5%E5%86%85%7C2017%7C1%7C%E6%98%AF%7C%E9%BB%91%7C2%7C60%7C40%E4%B8%87%E4%BB%A5%E4%B8%8A%22%2C%22cjshijian%22%3A%22%22%2C%22cateapplyed%22%3A%2229%22%2C%22gobquzhi%22%3A%22brand%5Cu003d%25E5%25AE%259D%25E9%25A9%25AC%5Cu0026chexi%5Cu003d5%25E7%25B3%25BB%5Cu0026carchexing%5Cu003d2017%25E6%25AC%25BE535Li3.0%25E8%2587%25AA%25E5%258A%25A8%25E9%25A2%2586%25E5%2585%2588%25E5%259E%258B%5Cu0026cheshenyanse%5Cu003d%25E9%25BB%2591%5Cu0026chelingqj%5Cu003d1%25E5%25B9%25B4%25E4%25BB%25A5%25E5%2586%2585%5Cu0026buytime%5Cu003d2017%25E5%25B9%25B4%5Cu0026shangpaiyuefen%5Cu003d1%25E6%259C%2588%5Cu0026rundistance%5Cu003d2%5Cu0026rundistanceqj%5Cu003d1-3%25E4%25B8%2587%25E5%2585%25AC%25E9%2587%258C%5Cu0026minprice%5Cu003d60%5Cu0026minpriceqj%5Cu003d40%25E4%25B8%2587%25E4%25BB%25A5%25E4%25B8%258A%5Cu0026baoyang%5Cu003d%25E6%2598%25AF%5Cu0026cateapplyed%5Cu003d29%5Cu0026localapplyed%5Cu003d79%22%2C%22kanchecs%22%3A%221%22%2C%22type%22%3A%220%22%2C%22userid%22%3A%2232923224881676%22%2C%22caraddress%22%3A%22%E5%8C%97%E4%BA%AC%E6%9C%9D%E9%98%B3%E5%9B%BD%E8%B4%B8%E4%B8%8A%E6%B5%B7%E5%B8%82%E5%90%B4%E4%B8%AD%E8%B7%AF1258%E5%8F%B7%E4%B8%80%E6%A5%BC%E8%BF%91%E5%90%88%E5%B7%9D%E8%B7%AF%E5%8F%A3%22%2C%22kucheid%22%3A%22%22%2C%22sfdaikuan%22%3A%220%22%2C%22shangpainianyue%22%3A%22201701%22%2C%22vin%22%3A%22LFMAP22C4E0641728%22%2C%22brand%22%3A%22409261%22%2C%22goblianxiren%22%3A%22%E9%87%91%E4%BB%81%E5%8D%8E%22%2C%22chelingqj%22%3A%222017_9999%22%2C%22newcaraddress%22%3A%22%E4%B8%8A%E6%B5%B7%E5%B8%82%E5%90%B4%E4%B8%AD%E8%B7%AF1258%E5%8F%B7%E4%B8%80%E6%A5%BC%E8%BF%91%E5%90%88%E5%B7%9D%E8%B7%AF%E5%8F%A3%22%2C%22yczbpic1%22%3A%22%22%2C%22guohufeiyong%22%3A%22%22%2C%22hidPostParam%22%3A%220%22%2C%22baoyang%22%3A%22515673%22%2C%22vinstate%22%3A%22%22%2C%22yczhibao%22%3A%22525381%22%2C%22syshijian%22%3A%22%22%2C%22shangpaiyuefen%22%3A%22515681%22%2C%22fcookie%22%3A%225664f6a6-8070-4da3-a755-8e258276e810%22%2C%22chexi%22%3A%22409331%22%2C%22cheshenyanse%22%3A%221%22%2C%22shifoufufeifabu%22%3A%220%22%2C%22Content%22%3A%222017%E6%AC%BE+%E5%AE%9D%E9%A9%AC5%E7%B3%BB+535Li+%E9%A2%86%E5%85%88%E5%9E%8B+%5Cn%E5%93%88%E5%93%88%E5%93%88%E5%93%88%E5%93%88++%5Cn%22%2C%22installment%22%3A%22678612%22%2C%22yczbpic%22%3A%22%22%2C%22kclocalDiduan%22%3A%221195%22%2C%22pictag%22%3A%22%7C%7C%7C%7C%7C%22%2C%22gouchefapiao%22%3A%22%22%2C%22MinPriceqj%22%3A%2240_999999%22%2C%22postparam_userid%22%3A%2232923224881676%22%2C%22MinPrice%22%3A%2260%22%2C%22captcha_type%22%3A%22%22%2C%22chapaihao%22%3A%22%22%2C%22shifouyishou%22%3A%220%22%2C%22faburen%22%3A%221%22%2C%22captcha_input%22%3A%22%22%2C%22carchexing%22%3A%22912391%22%2C%22rundistanceqj%22%3A%221_3%22%2C%22Phone%22%3A%2218758188560%22%2C%22HiddenForPara%22%3A%22%22%2C%22erscpinpai%22%3A%22%E5%AE%9D%E9%A9%AC5%E7%B3%BB%22%2C%22kclocalArea%22%3A%221142%22%2C%22picdesc1%22%3A%22%22%2C%22picdesc2%22%3A%22%22%2C%22localapplyed%22%3A%2279%22%2C%22tingshoushijian%22%3A%22%22%2C%22picdesc5%22%3A%22%22%2C%22Title%22%3A%22%E5%AE%9D%E9%A9%AC+5%E7%B3%BB+2017%E6%AC%BE535Li3.0%E8%87%AA%E5%8A%A8%E9%A2%86%E5%85%88%E5%9E%8B%22%2C%22picdesc6%22%3A%22%22%2C%22chapaihao1%22%3A%22%22%2C%22GTID%22%3A%22%22%2C%22picdesc3%22%3A%22%22%2C%22Pic%22%3A%22%2Fp1%2Fbig%2Fn_v1bl2lwkmqpjifs5r6nvla.jpg%7C%2Fp1%2Fbig%2Fn_v1bl2lwxuupjifsjgelqmq.jpg%7C%2Fp1%2Fbig%2Fn_v1bkujjd4wpjiftxbzcupq.jpg%7C%2Fp1%2Fbig%2Fn_v1bl2lwwmxpjiftbjvu4hq.jpg%7C%2Fp1%2Fbig%2Fn_v1bl2lwwm2pjiftlzmfj5q.jpg%7C%2Fp1%2Fbig%2Fn_v1bl2lwwm6pjifsw6r3qia.jpg%22%2C%22picdesc4%22%3A%22%22%2C%22yzm%22%3A%22%22%2C%22picdesc7%22%3A%22%22%2C%22dengjizheng%22%3A%22%22%2C%22picdesc8%22%3A%22%22%2C%22cube_post_jsonkey%22%3A%22%7B%5C%22jzJson%5C%22%3Anull%2C%5C%22znJson%5C%22%3Anull%2C%5C%22zdJson%5C%22%3A%7B%5C%22infotop_time_price_string%5C%22%3A%5C%2224%2C47.52%3B72%2C139.71%2C142.56%3B168%2C316.01%2C332.64%3B360%2C655.78%2C712.80%3B720%2C1283.04%2C1425.60%5C%22%2C%5C%22infotop_hourconsume%5C%22%3A%5C%221.98%5C%22%2C%5C%22infotop_cateId%5C%22%3A%5C%2229%5C%22%2C%5C%22infotop_localId%5C%22%3A%5C%2279%5C%22%2C%5C%22infotop_categoryId%5C%22%3A%5C%220%5C%22%2C%5C%22infotop_cityId%5C%22%3A%5C%2279%5C%22%2C%5C%22infotop_userId%5C%22%3A%5C%2232923224881676%5C%22%2C%5C%22infotop_source%5C%22%3A%5C%221501%5C%22%2C%5C%22infotop_hotCoafficient%5C%22%3A%5C%221.5%5C%22%2C%5C%22selectTime%5C%22%3A%5C%22%5C%22%2C%5C%22allConsume%5C%22%3A%5C%220.00%5C%22%2C%5C%22couponPassword%5C%22%3A%5C%22%5C%22%2C%5C%22couponAmount%5C%22%3A%5C%220.00%5C%22%2C%5C%22selectEventId%5C%22%3A%5C%22%5C%22%2C%5C%22topType%5C%22%3A%5C%220%5C%22%2C%5C%22selectAutoTopVal%5C%22%3A%5C%221%5C%22%2C%5C%22canMakeInfoTop%5C%22%3A%5C%22true%5C%22%7D%2C%5C%22jzFee%5C%22%3A%5C%220%5C%22%2C%5C%22znFee%5C%22%3A%5C%220%5C%22%2C%5C%22zdFee%5C%22%3A%5C%220.00%5C%22%2C%5C%22jzCop%5C%22%3A%5C%220%5C%22%2C%5C%22znCop%5C%22%3A%5C%220%5C%22%2C%5C%22zdCop%5C%22%3A%5C%220.00%5C%22%2C%5C%22sourceId%5C%22%3A%5C%221501%5C%22%2C%5C%22productId%5C%22%3A%5C%220%5C%22%2C%5C%22secondCateId%5C%22%3A%5C%2229%5C%22%2C%5C%22dispSecondCateId%5C%22%3A%5C%220%5C%22%2C%5C%22cityId%5C%22%3A%5C%2279%5C%22%2C%5C%22dispCityId%5C%22%3A%5C%2279%5C%22%7D%22%2C%22xbsx%22%3A%22%22%2C%22pinpai%22%3A%22%22%2C%22qxshijian%22%3A%22%22%2C%22buytime%22%3A%222017%22%2C%22installmentbak%22%3A%220%22%2C%22guchejia%22%3A%22%22%2C%22escpcLocal%22%3A%22post.58%22%2C%22shangshishijian%22%3A%22%22%7D";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
	}
	
	@Test
	public void convert(){
		List<Integer> list = Arrays.asList(1,2,3,4);
		list.add(5);
		System.out.println(list);
	}
}
