package com.wuxi.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

public class SystemPropertyTest {

    @Test
    public void read() {
        try {
            System.out.println(System.getProperty("te"));
            System.out.println(System.getProperty("test"));

            System.out.println("SecurityManager: " + System.getSecurityManager());

            FileInputStream stream = new FileInputStream("/Users/dasouche/Desktop/err.txt");
            System.out.println(System.getProperty("file.encoding"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
