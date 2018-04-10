package com.wuxi.service;

import java.io.File;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.junit.Test;

import com.wuxi.util.CommonsUtil;

public class HttpTest {


	/**
	 * 说客编辑地址: http://shuoke.autohome.com.cn/author/BlogArticleEditor.html
	 * 
	 * 代表图上传地址： http://shuoke.autohome.com.cn/ashx/UploadImage.ashx?OperType=UploadPicture&Key=BlogArticleOrig&Size=-400
	 * http://www3.autoimg.cn/newsdfs/g20/M05/6D/AB/400x0_0_autohomecar__wKjBw1kMHS2AS3gAAAihGx8oUn8010.jpg
	 * 		  www3.autoimg.cn/newsdfs/g8/M04/8A/69/620x0_0_autohomecar__wKjBz1kMHaiAFRMUAAihGx8oUn8614.jpg
	 * 正文图片上传地址： http://shuoke.autohome.com.cn/uploadify/ashx/UploadImage_utf8.ashx?OperType=UploadPicture&key=ArticleContent&Size=-620
	 * 
	 * return: {"Success":1,"Message":"成功","Body":{"FileList":[{"Success":true,"Message":null,"InputName":"file","FilePath":"//www2.autoimg.cn/newsdfs/g13/M07/92/B3/620x0_0_autohomecar__wKjBylkMLJaASquZAAihGx8oUn8009.jpg","Width":620,"Height":827,"ActualWidth":0,"ActualHeight":0}]}}
	 */
	@Test
	public void uploadFile(){
		//File f = CommonsUtil.getFileByUrl("http://img.souche.com/files/default/bd93bd76e7969dc1e98263a4b9ca61f4.jpg", "/Users/dasouche/Downloads/aa.jpg");
		File f = new File("/Users/dasouche/Downloads/3.jpg");
		PostMethod filePost = new PostMethod("https://up.qbox.me/");
		GZIPInputStream gis = null;
		try {
			Part image = new FilePart("file", "aa.jpg", f, "image/jpeg", "utf-8");
			Part token = new StringPart("token", "6VId7mt7zh2bN-I-bmp0p25aXjJUeIB3djNlRv6g:aTskAf50diI2jvLrQtZJWwn3y68=:eyJzY29wZSI6ImNhcnVwbG9hZCIsImRlYWRsaW5lIjoxNTExMjQ0MDc2fQ==", "utf-8");
			Part key = new StringPart("key", "source/carupload/photo/2017/1121/11/20171121110113905807.jpg","utf-8");
			Part[] parts = { image,token};
			filePost.addRequestHeader("origin", "https://www.iautos.cn");
			//filePost.addRequestHeader("Cookie", "fvlid=1478851514874x7WkbJeK; mallsfvi=1478851514874x7WkbJeK%7Cwww.baidu.com%7C238813433; sessionid=7B1AA78F-A35B-4DFB-A7C9-3036A7FA2ECD%7C%7C2016-11-11+16%3A04%3A58.293%7C%7Cwww.baidu.com; _ga=GA1.3.1851741995.1478851525; sfvi=1492398522063grSnIOZh%7Chttp%3A%2F%2Fwww.autohome.com.cn%2Fhangzhou%2F; sessionfid=2952909855; ahpau=1; sessionip=61.153.7.250; iknow=top100=1&myinfo=1&advance=1; skviewid=531557%2C504243; sessionuid=7B1AA78F-A35B-4DFB-A7C9-3036A7FA2ECD%7C%7C2016-11-11+16%3A04%3A58.293%7C%7Cwww.baidu.com; __utma=1.1851741995.1478851525.1493968471.1493970534.17; __utmb=1.0.10.1493970534; __utmc=1; __utmz=1.1493970534.17.9.utmcsr=shuoke.autohome.com.cn|utmccn=(referral)|utmcmd=referral|utmcct=/; pcpopclub=0271D24063BECFC7A188286522D3667EF0E669BA780295707C05320213E16D8368BBE93DE95F35298C14FBD234D68005CBC8CB6DAE1D932255E4D43C79DB87A64DBCEA5B5A491E240AE59E14118F133D2745D7E6F0C13FC736137B65EAF3C10D0588D86D0D5EA7537A701584567556553E25CFDBFCB4C9AE66C6C230CA174D8B2778D3C97F37EE9175073B7E251C444A779F330E2E10E4C3AF63A21ADABB54CF36F7FCF8EA28FB904CB000A2A31901ABBBC91B5F4FEE19E5AD10D0A36595FE955B83511A96D98B5849ED50EB0D47591BC9C211EE39698C38141AFBF2FEB8277B65B072FE3CAB191B30FC6DB4960B9813719B12F29ED12DAB2CA63C6D3869CC3386EBB14D82750C963787280B1660DCA414DAD9C618E81CEA70D18335FC12A8E8F94F34F2; clubUserShow=5793684|564|30|justzt|0|0|0||2017-05-05 15:51:47|0; autouserid=5793684; sessionuserid=5793684; sessionlogin=61256a1b1c8d4df5bf8d2f917290c83900586794; ahpvno=73; slvi=0%7Chttp%3A%2F%2Faccount.autohome.com.cn%2F%3Fbackurl%3Dhttp%253A%252F%252Fshuoke.autohome.com.cn%252F; ref=www.baidu.com%7C0%7C0%7C0%7C2017-05-05+15%3A50%3A39.512%7C2017-03-02+15%3A54%3A54.843; sessionvid=BB06DD8F-1F97-44C3-B967-B4929D0A35D9; area=330199");
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient clients = new HttpClient();
			
			int status = clients.executeMethod(filePost);
			String body = filePost.getResponseBodyAsString();
			System.out.println(body);
			//System.out.println(filePost.getResponseHeader("content-Type"));
//			InputStream in = filePost.getResponseBodyAsStream();
//			BufferedInputStream bis = new BufferedInputStream(in);
//			StringBuffer sb = new StringBuffer();
//			try {
//				int num = -1;
//				byte[] buff2 = new byte[1024];
//				while ((num = bis.read(buff2)) != -1) {
//					sb.append(new String(buff2, 0, num, "gb2312"));
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				bis.close();
//			}
//			System.out.println(sb);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (gis != null) {
				try {
					gis.close();
				} catch (IOException e) {
				}
			}
			if(filePost != null)
				filePost.releaseConnection();
//			if(f.exists())
//				f.delete();
		}
		
	}
	
	/**
	 * 地址：http://shuoke.autohome.com.cn/user/ashx/SaveArticle.ashx
	 * form data
	 *  ver:141208
		blogId:0
		blogTitle:大飞机首发
		content:<p style="text-align: center;"><img src="http://www2.autoimg.cn/newsdfs/g17/M15/8B/58/620x0_1_autohomecar__wKgH2FkMJFKAQgyVAAk5xYrJkZ4484.jpg" style="width:620px;height:465px"></p><p><br></p>
		summary:大飞机 //导语
		picture:http://www3.autoimg.cn/newsdfs/g23/M10/6F/35/160x120_0_autohomecar__wKjBwFkMJIGAcvIlAAEQabd-FKM896.jpg //代表图裁剪
		originalImg://www3.autoimg.cn/newsdfs/g23/M03/6F/35/400x0_0_autohomecar__wKjBwFkMJH6AN2H5AAp0yF3Eeog425.jpg  //代表图原图
		ow:400
		oh:300
		sw:353
		sh:265
		cutx:24
		cuty:17
		userRecommend:1 //推荐 1 必填推荐语 0 不推荐
		userRecommendNotes:首发
		IsFirst:2   //首发 1 独家 2 不选择 0
		IsPublish:2 //草稿 2

		return: {"returncode":0,"message":"添加成功","result":{"url":"//shuoke.autohome.com.cn/article/581443.html?033233"}}
		{"returncode":1020,"message":"您发表文章过多，请明天再来","result":{"url":""}}
		{"returncode":1010,"message":"您没有权限新增或修改文章","result":{"url":"//shuoke.autohome.com.cn/"}}
	 */
	@Test
	public void pushArticle(){
		PostMethod filePost = new PostMethod("http://shuoke.autohome.com.cn/user/ashx/SaveArticle.ashx");
		try {
			filePost.addRequestHeader("Cookie", "fvlid=1478851514874x7WkbJeK; mallsfvi=1478851514874x7WkbJeK%7Cwww.baidu.com%7C238813433; sessionid=7B1AA78F-A35B-4DFB-A7C9-3036A7FA2ECD%7C%7C2016-11-11+16%3A04%3A58.293%7C%7Cwww.baidu.com; _ga=GA1.3.1851741995.1478851525; sfvi=1492398522063grSnIOZh%7Chttp%3A%2F%2Fwww.autohome.com.cn%2Fhangzhou%2F; sessionfid=2952909855; ahpau=1; sessionip=61.153.7.250; iknow=top100=1&myinfo=1&advance=1; skviewid=531557%2C504243; sessionuid=7B1AA78F-A35B-4DFB-A7C9-3036A7FA2ECD%7C%7C2016-11-11+16%3A04%3A58.293%7C%7Cwww.baidu.com; __utma=1.1851741995.1478851525.1493968471.1493970534.17; __utmb=1.0.10.1493970534; __utmc=1; __utmz=1.1493970534.17.9.utmcsr=shuoke.autohome.com.cn|utmccn=(referral)|utmcmd=referral|utmcct=/; pcpopclub=0271D24063BECFC7A188286522D3667EF0E669BA780295707C05320213E16D8368BBE93DE95F35298C14FBD234D68005CBC8CB6DAE1D932255E4D43C79DB87A64DBCEA5B5A491E240AE59E14118F133D2745D7E6F0C13FC736137B65EAF3C10D0588D86D0D5EA7537A701584567556553E25CFDBFCB4C9AE66C6C230CA174D8B2778D3C97F37EE9175073B7E251C444A779F330E2E10E4C3AF63A21ADABB54CF36F7FCF8EA28FB904CB000A2A31901ABBBC91B5F4FEE19E5AD10D0A36595FE955B83511A96D98B5849ED50EB0D47591BC9C211EE39698C38141AFBF2FEB8277B65B072FE3CAB191B30FC6DB4960B9813719B12F29ED12DAB2CA63C6D3869CC3386EBB14D82750C963787280B1660DCA414DAD9C618E81CEA70D18335FC12A8E8F94F34F2; clubUserShow=5793684|564|30|justzt|0|0|0||2017-05-05 15:51:47|0; autouserid=5793684; sessionuserid=5793684; sessionlogin=61256a1b1c8d4df5bf8d2f917290c83900586794; ahpvno=73; slvi=0%7Chttp%3A%2F%2Faccount.autohome.com.cn%2F%3Fbackurl%3Dhttp%253A%252F%252Fshuoke.autohome.com.cn%252F; ref=www.baidu.com%7C0%7C0%7C0%7C2017-05-05+15%3A50%3A39.512%7C2017-03-02+15%3A54%3A54.843; sessionvid=BB06DD8F-1F97-44C3-B967-B4929D0A35D9; area=330199");
			filePost.addRequestHeader("Origin", "http://shuoke.autohome.com.cn");
			filePost.addRequestHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
			filePost.addRequestHeader("X-Requested-With", "XMLHttpRequest");
			filePost.addRequestHeader("Referer", "http://shuoke.autohome.com.cn/author/BlogArticleEditor.html");
			filePost.addRequestHeader("Host", "shuoke.autohome.com.cn");
			filePost.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			
			filePost.addParameter("blogTitle", "汽车之最强");
			filePost.addParameter("content","<p style=\"text-align: center;\"><img src=\"http://www2.autoimg.cn/newsdfs/g17/M15/8B/58/620x0_1_autohomecar__wKgH2FkMJFKAQgyVAAk5xYrJkZ4484.jpg\" style=\"width:620px;height:465px\"></p><p>开的最快</p><p><br></p>");
			filePost.addParameter("summary","如何保养爱车？");
			filePost.addParameter("userRecommend","1");
			filePost.addParameter("userRecommendNotes","经典");
			filePost.addParameter("picture","http://www3.autoimg.cn/newsdfs/g23/M10/6F/35/160x120_0_autohomecar__wKjBwFkMJIGAcvIlAAEQabd-FKM896.jpg");
			filePost.addParameter("IsFirst","1");
			filePost.addParameter("IsPublish","2");//草稿
			HttpClient clients = new HttpClient();
			
			int status = clients.executeMethod(filePost);
			String body = filePost.getResponseBodyAsString();
			System.out.println(body);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(filePost != null)
				filePost.releaseConnection();
		}
	}
	
}
