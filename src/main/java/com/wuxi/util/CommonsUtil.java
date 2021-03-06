package com.wuxi.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CommonsUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonsUtil.class);

    /**
     * 从网络获取文件,比如图片等
     *
     * @param targetUrl 文件网络地址
     * @param fileName  文件名
     * @return File 文件对象
     */
    public static File getFileByUrl(String targetUrl, String fileName) {
        if (StringUtils.isEmpty(targetUrl)) {
            throw new CoreException("Parameter is empty");
        }
        //new一个URL对象
        URL url;
        FileOutputStream outStream = null;
        File imageFile = null;
        try {
            url = new URL(targetUrl);
            //打开链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "close");
            System.setProperty("http.keepAlive", "false");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);

            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            imageFile = new File(fileName);
            //创建输出流
            outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (MalformedURLException e) {
            logger.error("error", e);
        } catch (IOException e) {
            logger.error("error", e);
        } catch (Exception e) {
            logger.error("error", e);
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    //
                }
            }
        }
        return imageFile;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
