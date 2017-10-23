package com.example.utils;

/**
 * Created by xmq on 2017/8/21.
 */

public class Utils {
    public static String printStr(Object str) {
        System.out.print(String.valueOf(str)  +"    thread:"+ Thread.currentThread().getName()+ "\n");
        return "";
    }
}
