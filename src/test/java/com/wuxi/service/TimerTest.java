package com.wuxi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //timer 启动单线程执行task，一个出错整个timer会被cancel
        try {
            timer.schedule(new ThrowTask(), 1);
        } catch (Exception e) {
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {

        }

        timer.schedule(new ThrowTask(), 1);

    }

    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
            throw new RuntimeException();
        }

    }

    @Test
    public void print() {
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}
