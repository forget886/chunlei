/**
 * 
 */
package com.wuxi.service;
import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.util.ParallelSorter;

/**
 * @author dasouche
 * @date 2017年7月14日上午10:32:09
 * TODO
 */
public class HuaxiaTest {

	private static final Logger logger = LoggerFactory.getLogger(HuaxiaTest.class);
	
	@Test
	public void push() {
		PostMethod post = new PostMethod("http://www.hx2car.com/vip/savecar.htm");
		post.addRequestHeader("Cookie", "unique_cookie=1499996159234578566; hx2car_web=MTg3NTgxODg1NjAnLS0tJ2ZkMzMyZDNkMzdjYjViZGRhZWY5M2ZjYjM3NDQ5NDcy; Hm_lvt_5b941f460f2ec63730c9f325ba44961f=1499996159; Hm_lpvt_5b941f460f2ec63730c9f325ba44961f=1499996415");
//		post.addRequestHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
//		post.addRequestHeader("Refer", "http://passport.58.com/login?path=http%3A%2F%2Fmy.58.com%2Findex");
		
		post.addParameter("Content-type", "multipart/form-data; boundary=----WebKitFormBoundaryARGBAoZLcfpLslhS");
//		post.addParameter("path","http://my.58.com/index");
		post.addParameter("carKind","0");
		post.addParameter("bigType","1");
		post.addParameter("useYear","2017");
		post.addParameter("useMonth","6");
		post.addParameter("carSrial_0", "奥迪");
		post.addParameter("parSerial", "26");
		post.addParameter("brandStr", "奥迪Q3");
		post.addParameter("sonSerial", "11191");
		post.addParameter("typeStr", "Q3 2.0TFSI 40TFSI 运动型");
		post.addParameter("carType", "826652");
		post.addParameter("money", "13");
		post.addParameter("journey", "12");
		post.addParameter("color", "2");
		post.addParameter("carAuto", "1");
		post.addParameter("oilWear", "1");
		post.addParameter("transfer", "1");
		post.addParameter("standard", "5");
		post.addParameter("carSource", "0");
		post.addParameter("picUrls", "/newimg1/M01/D1/EE/Clo8w1lQdq-AW0UJAAiuqsJ9TuY536.jpg");
		post.addParameter("picUrls", "/newimg1/M02/D1/EF/Clo8w1lQdraAQ2bdAAmuY3fIsdE847.jpg");
		
		
		HttpClient client = getHttpClient();
		
		try {
			int status = client.executeMethod(post);
			
			for(Header header : post.getResponseHeaders()){
				logger.info(header.getName() + " : " + header.getValue());
			}
			logger.info(post.getResponseBodyAsString());
			/**
			 * <html xmlns="http://www.w3.org/1999/xhtml">
				<head>
				    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				</head>
				
				<body>
					<script type="text/javascript">
						//输出错误提示
						var message="";
						if(message){
						 alert(message);
						}
						//跳转到页面
						var redirectUrl="/vip/manageronsell.htm";
						if(message=='华币不足车辆信息发布失败!')
						redirectUrl='/vip/hbrecharge.htm';
						if(redirectUrl){
							if (navigator.appVersion.match(/6./i)=="6.") {
						         var link = document.createElement("a");
						         link.href = redirectUrl;
						         link.style.display = 'none';
						         document.body.appendChild(link);
						         try{
						        	 link.click();
						         }catch(err){
						         	window.location.href = redirectUrl;
						         }
						     } else {
						         window.location.href = redirectUrl;
						     }
						}
					
					</script>
			</body>
			</html>

			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
			
	protected HttpClient getHttpClient(){
		HttpClientParams params = new HttpClientParams();
		params.setSoTimeout(10000);
		params.setConnectionManagerTimeout(10000);
	
 		HttpClient client = new HttpClient(params,new SimpleHttpConnectionManager(true));
		return client;
 	}
	
}
