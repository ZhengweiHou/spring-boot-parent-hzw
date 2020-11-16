package com.hzw.learn.springboot.jvm.tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test1 {

    public static void main(String[] args) {


        System.out.println((long)(0 * 9 * Math.pow(10,14)) + (long)Math.pow(10,14));

        System.out.println((long)(Math.random() * 9 * Math.pow(10,14)) + (long)Math.pow(10,14));

        System.out.println((long)(0.1 * 9 * Math.pow(10,14)) + (long)Math.pow(10,14));

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(new Date()));


        String flag = "B";

        switch (flag){
            case "A":{
                System.out.println("A");
                break;
            }
            case "B":{
                System.out.println("B");
            }
            case "C": {
                System.out.println("C");
                break;
            }
            case "D":{
                System.out.println("D");
            }
            default:{}
        }

//        for (int i=0; i<100000000; i++){
//            double a = Math.random();
//            if (a==0){
//                System.out.println(a);
//                break;
//            }
////            System.out.println(Math.random());
////                    System.out.println(Math.random() * 9 * Math.pow(10,14));
//        }
    }
}
