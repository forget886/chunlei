package com.wuxi.service;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Time;

import org.junit.Test;

public class CommonTest {

	@Test
	public void dateTest(){
		
	}
	
	@Test
	public void UrlDecode(){
		String url = "http://erp.souche.com/pc/car/carstockaction/operate.json?carId=aad8952aeaf84e33a3745efca5221973&from=pc&pushInfos[]=%7B%22bindUserId%22:220735,%22syncContactInfoId%22:63968,%22platform%22:%2258%22%7D&pushInfos[]=%7B%22bindUserId%22:220737,%22syncContactInfoId%22:63978,%22platform%22:%22taoche%22%7D&pushInfos[]=%7B%22bindUserId%22:220760,%22syncContactInfoId%22:63987,%22platform%22:%22sina%22%7D&status=0&token=1495694470353346&type=1&syncAutoUpdates=%7B%2258%22:%221%22,%22taoche%22:%221%22,%22sina%22:%221%22%7D";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
	}
}
