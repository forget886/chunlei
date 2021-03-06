package com.wuxi.service;

import java.util.Arrays;

import org.junit.Test;

public class SortTest {

    @Test
    public void sortMP() {
        int data[] = {23, 2, 45, 3, 1, 6, 8, 0, 34};
        //总共跑data.length-1趟
        for (int i = 0; i < data.length - 1; i++) {
            //每趟比较data.length-1-i次
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void insertSort() {
        int data[] = {23, 2, 45, 3, 1, 6, 8, 0, 34};
        int p;
        for (int i = 1; i < data.length; i++) {
            int tmp = data[i];
            for (p = i; p > 0 && tmp < data[p - 1]; p--) {
                data[p] = data[p - 1];
            }
            data[p] = tmp;
        }
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void quickSortTest() {
        int data[] = {23, 2, 45, 3, 1, 6, 8, 0, 34};
        quickSort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));
    }

    public void quickSort(int[] data, int start, int end) {
        if (start < end) {
            int i = start;
            int j = end;
            int tmp = data[start];
            while (i < j) {
                while (j > i && data[j] > tmp)
                    j--;
                data[i] = data[j];
                while (i < j && data[i] < tmp)
                    i++;
                data[j] = data[i];
            }
            data[i] = tmp;
            quickSort(data, start, i - 1);
            quickSort(data, i + 1, end);
        }

    }

    @Test
    public void halfQueryTest() {
        int data[] = {0, 1, 2, 3, 6, 8, 23, 34, 45};
        int key = 34;
        int start = 0;
        int end = data.length - 1;
        while (start <= end) {
            int half = (start + end) / 2;
            System.out.println(data[half]);
            if (data[half] == key) {
                System.out.println(key);
                break;
            } else if (key > data[half]) {
                start = half + 1;
            } else {
                end = half - 1;
            }
        }
        System.out.println("over");
    }


}
