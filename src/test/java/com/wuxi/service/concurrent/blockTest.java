package com.wuxi.service.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

import org.junit.Test;

public class blockTest {

    @Test
    public void put() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 20; i++) {
            try {
                System.out.println(queue.offer(i));
                System.out.println("插入 " + i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @Test
    public void aqsTest() {

    }
}


class MyQueuedSynchronizer extends AbstractQueuedSynchronizer {

}