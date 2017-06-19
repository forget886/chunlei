package com.wuxi.service;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CommonTest {

	@Test
	public void dateTest(){
		String string = "{\"bizExt\":{\"url\":\"/postsuccess/30301133085133/?source=car\"},\"bizCode\":\"redirect\"}";
		System.out.println(getThirdId(string));
	}
	
	//{"bizExt":{"url":"/postsuccess/30301133085133/?source=car"},"bizCode":"redirect"}
		private String getThirdId(String url){
			int start = url.indexOf("success/");
			int end = url.indexOf("/?s");
			return url.substring(start+"success/".length(), end);
		}
	
	@Test
	public void UrlDecode(){
		String url = "%7B%22Title%22:%22%E5%A4%A7%E4%BC%97%20POLO%202004%E6%AC%BE%201.4%20%E6%89%8B%E5%8A%A8%20%E8%88%92%E9%80%82%E5%9E%8B%E4%B8%89%E5%8E%A2%22,%22Phone%22:%2218758188560%22,%22Content%22:%22%E5%A4%A7%E4%BC%97%202004%E6%AC%BE%20POLO%20%E4%B8%89%E5%8E%A2%201.4L%20%E6%89%8B%E5%8A%A8%E8%88%92%E9%80%82%E5%9E%8B%20%5Cn%20%5Cn%22,%22isBiz%22:%221%22,%22localArea%22:%2218%22,%22localDiduan%22:%22%22,%22Pic%22:%22/p1/big/n_v1bl2lwwpkh43fs4ibz5uq.jpg%7C/p1/big/n_v1bkuymc7nh43ftqikzjea.jpg%7C/p1/big/n_v1bkujjd7oh43fs7vyizjq.jpg%7C/p1/big/n_v1bj3gzr7rh43fsprfqzka.jpg%7C/p1/big/n_v1bkuymc7th43fs5alnaga.jpg%7C/p1/big/n_v1bj3gzr7wh43fsd3pgbgq.jpg%22,%22userid%22:%2232923224881676%22,%22IM%22:%22%22,%22paixu-xxzl%22:%2288000000%22,%22pictag%22:%22%7C%7C%7C%7C%7C%22,%22shifouyishou%22:%220%22,%22escwltv2%22:%220%22,%22MinPrice%22:%224%22,%22buytime%22:%222004%22,%22ObjectType%22:%221%22,%22cspailiang%22:%221.4%22,%22biansuqi%22:%22%E6%89%8B%E5%8A%A8%22,%22goblianxiren%22:%22%E9%87%91%E6%9F%90%22,%22gobalsokey%22:%22%E5%A4%A7%E4%BC%97%7CPOLO%7C2004%E6%AC%BE1.4%E6%89%8B%E5%8A%A8%E8%88%92%E9%80%82%E5%9E%8B%E4%B8%89%E5%8E%A2%7C3-5%E4%B8%87%E5%85%AC%E9%87%8C%7C10%E5%B9%B4%E4%BB%A5%E4%B8%8A%7C2004%7C4%7C%E6%98%AF%7C%E9%93%B6%7C5%7C3-5%E4%B8%87%7CD%7C%E8%BD%BF%E8%BD%A6%7C%E5%A4%A7%E4%BC%97POLO%7C%E5%90%88%E8%B5%84%7C1.4%7C%E5%9B%BD%E2%85%A1%7C%E6%89%8B%E5%8A%A8%7C2003%22,%22gobindexeffectivedate%22:%222017-10-06%2013:40:02%22,%22brand%22:%22411611%22,%22chexi%22:%22412045%22,%22carchexing%22:%22846207%22,%22MinPriceqj%22:%223_5%22,%22rundistanceqj%22:%223_5%22,%22chelingqj%22:%221900_2007%22,%22paifangbiaozhun%22:%22202%22,%22displacement%22:%22408988%22,%22gearbox%22:%22408815%22,%22madein%22:%22408852%22,%22type%22:%220%22,%22cheshenyanse%22:%223%22,%22chexibieming%22:%22518402%22,%22shangshishijian%22:%22515722%22,%22baoyang%22:%22515673%22,%22shangpaiyuefen%22:%22515684%22,%22yczhibao%22:%22525381%22,%22rundistance%22:%225%22,%22kanchecs%22:%221%22,%22gobcookieid%22:%22c5/nn1kkpDhdQ6V8fOy2Ag%22,%22fist_postsourceid%22:%2200000000%22,%22kclocalDiduan%22:%221195%22,%22kclocalArea%22:%221142%22,%22sfdaikuan%22:%220%22,%22shangpainianyue%22:%22200404%22,%22installment%22:%22678612%22,%22caraddress%22:%22%E5%8C%97%E4%BA%AC%E6%9C%9D%E9%98%B3%E5%9B%BD%E8%B4%B8%E4%B8%8A%E6%B5%B7%E5%B8%82%E5%90%B4%E4%B8%AD%E8%B7%AF1258%E5%8F%B7%E4%B8%80%E6%A5%BC%E8%BF%91%E5%90%88%E5%B7%9D%E8%B7%AF%E5%8F%A3%22,%22newcaraddress%22:%22%E4%B8%8A%E6%B5%B7%E5%B8%82%E5%90%B4%E4%B8%AD%E8%B7%AF1258%E5%8F%B7%E4%B8%80%E6%A5%BC%E8%BF%91%E5%90%88%E5%B7%9D%E8%B7%AF%E5%8F%A3%22,%22postsourceid%22:%2200000000%22,%22carname%22:%22%E5%A4%A7%E4%BC%97%20POLO%202004%E6%AC%BE%201.4%20%E6%89%8B%E5%8A%A8%20%E8%88%92%E9%80%82%E5%9E%8B%E4%B8%89%E5%8E%A2%22,%22goblastEditClient%22:%2200000000%22,%22tiaojiajilu%22:%222017-06-06%2013:40:02=4%22,%22cpcallstate%22:%220%22,%22zcbqstate%22:%2200%22,%22tzsfguoqi%22:%221%22,%22gobquzhi%22:%22brand=%25E5%25A4%25A7%25E4%25BC%2597&amp;chexi=POLO&amp;carchexing=2004%25E6%25AC%25BE1.4%25E6%2589%258B%25E5%258A%25A8%25E8%2588%2592%25E9%2580%2582%25E5%259E%258B%25E4%25B8%2589%25E5%258E%25A2&amp;cheshenyanse=%25E9%2593%25B6&amp;chelingqj=10%25E5%25B9%25B4%25E4%25BB%25A5%25E4%25B8%258A&amp;buytime=2004%25E5%25B9%25B4&amp;shangpaiyuefen=4%25E6%259C%2588&amp;rundistance=5&amp;rundistanceqj=3-5%25E4%25B8%2587%25E5%2585%25AC%25E9%2587%258C&amp;minprice=4&amp;minpriceqj=3-5%25E4%25B8%2587&amp;baoyang=%25E6%2598%25AF&amp;cateapplyed=29&amp;localapplyed=18&amp;zimu=D&amp;objecttype=%25E8%25BD%25BF%25E8%25BD%25A6&amp;chexibieming=%25E5%25A4%25A7%25E4%25BC%2597POLO&amp;madein=%25E5%2590%2588%25E8%25B5%2584&amp;displacement=1.4&amp;paifangbiaozhun=%25E5%259B%25BD%25E2%2585%25A1&amp;gearbox=%25E6%2589%258B%25E5%258A%25A8&amp;shangshishijian=2003%22,%22shifoufufeifabu%22:%220%22,%22fbyh%22:%221%22,%22shifougaodang%22:%220%22,%22isganjihaoche%22:%220%22,%22pailiangs%22:%2210_16%22,%22longitude%22:%22117.210813%22,%22glatitude%22:%2239.13802804835594%22,%22latitude%22:%2239.14393%22,%22glongitude%22:%22117.20426324566455%22%7D";
		try {
			url = URLDecoder.decode(url,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(url);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void convert(){
		String str = "{\"name\":\"aa\",\"name\":\"bb\",\"pass\":\"aa\"}";
		JSONObject object = JSONObject.fromObject(str);
		JSONArray array = object.getJSONArray("name");
		System.out.println(array.isArray());
	}
}
