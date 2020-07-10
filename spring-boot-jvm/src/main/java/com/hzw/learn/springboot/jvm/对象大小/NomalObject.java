package com.hzw.learn.springboot.jvm.对象大小;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName NomalObject
 * @Description 普通对象
 * @Author houzw
 * @Date 2020/7/8
 **/
public class NomalObject {
    int inta=100;
    int intb=100;
//    String stra="hzw";  // 字符串是用字符数组实现的，这里若有字符串变量的话，对象就会是数组对象

    public static void main(String[] args) {
        NomalObject nomalObject = new NomalObject();
        System.out.println(ClassLayout.parseInstance(nomalObject).toPrintable());
    }
}

//    ==默认开启指针压缩:-XX:+UseCompressedOops==
//com.hzw.learn.springboot.jvm.对象大小.NomalObject object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
//        12     4    int NomalObject.inta                          100
//        16     4    int NomalObject.intb                          100
//        20     4        (loss due to the next object alignment)
//        Instance size: 24 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

//    ==关闭指针压缩：-XX:-UseCompressedOops==
//com.hzw.learn.springboot.jvm.对象大小.NomalObject object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           28 50 7d e8 (00101000 01010000 01111101 11101000) (-394440664)
//        12     4        (object header)                           d5 7f 00 00 (11010101 01111111 00000000 00000000) (32725)
//        16     4    int NomalObject.inta                          100
//        20     4    int NomalObject.intb                          100
//        Instance size: 24 bytes
//        Space losses: 0 bytes internal + 0 bytes external = 0 bytes total

