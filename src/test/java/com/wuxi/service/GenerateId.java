package com.wuxi.service;

import org.junit.Test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 青铜
 * @create: 2018-05-28
 **/
public class GenerateId {

    private final static String    DATE_FORMAT = "yyMMddHHmmssSSS";

    /**
     * 返回26位含时间戳序列：17位时间戳+3位服务器IP+6位序列号
     *
     * @return
     * @throws Exception
     */
    @Test
    public void generate() throws Exception{
        String seqName = "";
        String seq = new SimpleDateFormat(DATE_FORMAT).format(new Date());

        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println(ip);
        String[] ips = ip.split("\\.");
        StringBuffer sb = new StringBuffer(seq);
        sb.append(getFixLength(ips[3],3));
        System.out.println(sb.toString());
    }

    @Test
    public void fix(){
        System.out.println(getFixLength("abc",10));
    }

    private String getFixLength(String seq ,int length){
        StringBuilder sb = new StringBuilder();
        int remainLength = length - seq.length();
        if (seq.length() < remainLength) {
            for (int i = 0; i < remainLength - seq.length(); i++) {
                sb.append("0");
            }
            sb.append(seq);
        } else {
            sb.append(seq.substring(0, remainLength));
        }
        return sb.toString();
    }

}
