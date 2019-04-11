package com.wuxi.service;

import com.wuxi.util.StringUtil;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class HttpRequestImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRequestImplTest.class);


    @Test
    public void sendTest() {
        String url = "http://www.baidu.com";
        int qps = 3000;
        int time = 1;
        String path = "/Users/qingtong/Desktop/request.txt";
        send(qps, url, time, path);
    }


    public void send(int qps, final String url, int time, String path) {
        if (qps <= 0 || StringUtil.isEmpty(url) || time <= 0 || StringUtil.isEmpty(path)) {
            return;
        }
        final String logFormat = "%s 耗时：%d ms";

        //创建HTTP的连接池管理对象
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        //将最大连接数增加到200
        connectionManager.setMaxTotal(2000);
        //将http://www.baidu.com:80的最大连接增加到50
        //HttpHost httpHost = new HttpHost("http://www.baidu.com",80);
        //connectionManager.setMaxPerRoute(new HttpRoute(httpHost),50);
        //同一个请求地址并发数
        connectionManager.setDefaultMaxPerRoute(2000);

        //HttpClient对象
        final CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setRetryHandler(retryHandler(0)).build();

        CompletionService<String> completionService = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(2000));
        for (int i = 0; i < qps; i++) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
                    long start = System.currentTimeMillis();
                    doGet(url, httpClient);
                    return String.format(logFormat, format.format(new Date()), (System.currentTimeMillis() - start));
                }
            });
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        File file = new File(path);
        BufferedWriter writer = null;
        long deadline = System.currentTimeMillis() + 1000 * time;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < qps; i++) {
                if (System.currentTimeMillis() > deadline) {
                    System.out.println("结束:" + i);
                    break;
                }
                try {
                    Future<String> future = completionService.take();
                    if (future != null) {
                        try {
                            writer.write(future.get());
                            writer.newLine();
                            //System.out.println(i);
                        } catch (FileNotFoundException e) {
                            LOG.error("", e);
                        } catch (IOException e) {
                            LOG.error("", e);
                        }
                    }
                } catch (Exception e) {
                    LOG.error("", e);
                }
            }

        } catch (IOException e1) {
            LOG.error("", e1);
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                } catch (IOException e1) {
                    LOG.error("", e1);
                }
                try {
                    writer.close();
                } catch (IOException e) {
                    LOG.error("", e);
                }
            }

        }
    }

    private void doGet(final String url, CloseableHttpClient httpClient) {


        HttpGet request = new HttpGet(url);
        // 构建请求配置信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(300) // 创建连接的最长时间
                .setConnectionRequestTimeout(200) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(4 * 1000) // 数据传输的最长时间4s
                .build();
        request.setConfig(config);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //System.out.println(content);
            }
        } catch (Exception e) {
            LOG.error("", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 请求重试处理
     *
     * @param tryTimes
     * @return
     */
    private static HttpRequestRetryHandler retryHandler(final int tryTimes) {
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                // 如果已经重试了n次，就放弃
                if (executionCount >= tryTimes) {
                    return false;
                }
                // 如果服务器丢掉了连接，那么就重试
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                // 不要重试SSL握手异常
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                // 超时
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                // 目标服务器不可达
                if (exception instanceof UnknownHostException) {
                    return true;
                }
                // 连接被拒绝
                if (exception instanceof ConnectTimeoutException) {
                    return false;
                }
                // SSL握手异常
                if (exception instanceof SSLException) {
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                org.apache.http.HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        return httpRequestRetryHandler;
    }


}
