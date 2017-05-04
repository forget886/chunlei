package com.wuxi.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.junit.Test;

import com.wuxi.util.CommonsUtil;

public class HttpTest {



	@Test
	public void tt(){
		File f = CommonsUtil.getFileByUrl("http://img.souche.com/files/default/bd93bd76e7969dc1e98263a4b9ca61f4.jpg", "/Users/dasouche/Downloads/aa.jpg");
		
		PostMethod filePost = new PostMethod("http://upload.hx2car.com/car/upload.json");
		GZIPInputStream gis = null;
		try {
			Part image = new FilePart("file", "aa.jpg", f, "image/jpeg", "utf-8");
			Part[] parts = { image };
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient clients = new HttpClient();
			
			int status = clients.executeMethod(filePost);
			
			InputStream in = filePost.getResponseBodyAsStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			bis.mark(100);

			StringBuffer sb = new StringBuffer();
			try {
				int num = -1;
				byte[] buff2 = new byte[1024];
				bis.reset();
				while ((num = bis.read(buff2)) != -1) {
					sb.append(new String(buff2, 0, num, "utf-8"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				bis.close();
			}
			System.err.println(sb);

		} catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (Exception e2) {
			}
		} finally {
			if (gis != null) {
				try {
					gis.close();
				} catch (IOException e) {
				}
			}
			if(filePost != null)
				filePost.releaseConnection();
			if(f.exists())
				f.delete();
		}
		
	}
}
