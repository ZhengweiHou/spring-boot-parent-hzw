package com.hzw.learn.springboot.jvm.对象大小;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName NullObject
 * @Description 空对象：没有任何普通属性的类生成的对象
 * @Author houzw
 * @Date 2020/7/7
 **/
public class NullObject {
    public static void main(String[] args) {
        NullObject nullObject = new NullObject();
        System.out.println(ClassLayout.parseInstance(nullObject).toPrintable());
    }
}

//    ==默认开启指针压缩:-XX:+UseCompressedOops==
//    com.hzw.learn.springboot.jvm.对象大小.NullObject object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
//        12     4        (loss due to the next object alignment)
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes tota

//    ==关闭指针压缩：-XX:-UseCompressedOops==
//    com.hzw.learn.springboot.jvm.对象大小.NullObject object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           28 30 fa f7 (00101000 00110000 11111010 11110111) (-134598616)
//        12     4        (object header)                           bf 7f 00 00 (10111111 01111111 00000000 00000000) (32703)
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 0 bytes external = 0 bytes total