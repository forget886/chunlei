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
	public void UrlDecode(){
		String url = "%7B%0A%20%20%20%20%22traceId%22:%20%223iQz7E7I70dEj7JW%22,%0A%20%20%20%20%22headers%22:%20%7B%0A%20%20%20%20%20%20%20%20%22date%22:%20%22Fri,%2028%20Jul%202017%2007:18:33%20GMT%22,%0A%20%20%20%20%20%20%20%20%22content-length%22:%20%220%22,%0A%20%20%20%20%20%20%20%20%22server%22:%20%22nginx%22,%0A%20%20%20%20%20%20%20%20%22set-cookie%22:%20%22PPU=%5C%22UID=32923224881676&PPK=c2d3fbb3&PPT=2424527b&SK=7A8AA9BF524FE054771C1A9789A46513EDB4D6E511FBC639E&LT=1501226313393&UN=npmcw_p1&LV=b4662b94&PBODY=OozsUdgae2NJ5k0QYboFNpkDM0a3MBa24Cd-ALMj2-3CVWz_8U8iCl8Urb7HsRipp_RB9YyIXWZRrEySQSqf7jZ_obBYGjGKBTBUZULCOr3AHYZNCDYQ3b4rygBW2oD8ejpgRjHHFy8qpbqE9hxM-p17a4WxVkoolPnhtyl1fbk&VER=1%5C%22;%20Version=1;%20Domain=58.com;%20Path=/,passport=%5C%22UID=32923224881676&PPK=c2d3fbb3&PPT=2edea038&SK=08CDD29DF3C97588726630F478E6C6FDA92876A373AEE3538&LT=1501226313395&UN=npmcw_p1&LV=b4662b94&PBODY=OozsUdgae2NJ5k0QYboFNpkDM0a3MBa24Cd-ALMj2-3CVWz_8U8iCl8Urb7HsRipp_RB9YyIXWZRrEySQSqf7jZ_obBYGjGKBTBUZULCOr3AHYZNCDYQ3b4rygBW2oD8ejpgRjHHFy8qpbqE9hxM-p17a4WxVkoolPnhtyl1fbk&VER=1%5C%22;%20Version=1;%20Domain=passport.58.com;%20Max-Age=1209600;%20Expires=Fri,%2011-Aug-2017%2007:18:33%20GMT;%20Path=/,58cooper=%5C%22userid=32923224881676&username=npmcw_p1&cooperkey=51091a1e9a69ca92003ca03d5478b800%5C%22;%20Version=1;%20Domain=58.com;%20Path=/,www58com=%5C%22AutoLogin=true&UserID=32923224881676&UserName=npmcw_p1&CityID=0&Email=&AllMsgTotal=0&CommentReadTotal=0&CommentUnReadTotal=0&MsgReadTotal=0&MsgUnReadTotal=0&RequireFriendReadTotal=0&RequireFriendUnReadTotal=0&SystemReadTotal=0&SystemUnReadTotal=0&UserCredit=0&UserScore=0&PurviewID=&IsAgency=false&Agencys=null&SiteKey=887EB60F8846773DB916A9A3230516CBC1840CCEE286986AC&Phone=&WltUrl=&UserLoginVer=53115061EEDA8F62CBB2E610F1149366F&LT=1501226313395%5C%22;%20Version=1;%20Domain=58.com;%20Path=/,pptuname=18758188560;%20Expires=Sun,%2027-Aug-2017%2007:18:33%20GMT;%20Path=/,vipsessionkey=42c82e01ffb6483895bb7069353871b7;%20Path=/%22,%0A%20%20%20%20%20%20%20%20%22expires%22:%20%22-1%22,%0A%20%20%20%20%20%20%20%20%22cachecontrol%22:%20%22no-cache%22,%0A%20%20%20%20%20%20%20%20%22location%22:%20%22http://passport.58.com/pptcrossdomainagent?tokeykey=tJHPmUKr5OH1p8o7ewbtsb_ie5LVSLVa%22,%0A%20%20%20%20%20%20%20%20%22p3p%22:%20%22CP=%5C%22CURa%20ADMa%20DEVa%20PSAo%20PSDo%20OUR%20BUS%20UNI%20PUR%20INT%20DEM%20STA%20PRE%20COM%20NAV%20OTC%20NOI%20DSP%20COR%5C%22%22,%0A%20%20%20%20%20%20%20%20%22pragma%22:%20%22no-cache%22%0A%20%20%20%20%7D,%0A%20%20%20%20%22method%22:%20%22post%22,%0A%20%20%20%20%22body%22:%20%7B%7D,%0A%20%20%20%20%22url%22:%20%22https://passport.58.com/login/dologin%22,%0A%20%20%20%20%22_msgId%22:%20%220A8933B200002A9F0000097E4F3744B8%22,%0A%20%20%20%20%22statusCode%22:%20302%0A%7D";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
		JSONObject object = JSONObject.fromObject(url);
        JSONObject headers = object.getJSONObject("headers");
        Object[] objects = null;
	      try {
				String cookieStr = headers.getString("set-cookie");
				String[] cookies = cookieStr.split(",");
				
				objects = new Object[cookies.length]; 
				for(int i=0;i<cookies.length;i++){
					objects[i] = cookies[i];
					System.out.println(cookies[i]);
				}
			} catch (Exception e) {
				if(e instanceof JSONException){
					JSONArray cookieArray = headers.getJSONArray("set-cookie");
	             objects = cookieArray.toArray();
				}else {
					throw e;
				}
			}
	}
	
	@Test
	public void convert(){
		List<Integer> list = Arrays.asList(1,2,3,4);
		list.add(5);
		System.out.println(list);
	}
}
