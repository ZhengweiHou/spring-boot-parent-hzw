package com.hzw.learn.springboot.jvm.对象大小;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName ArrayObject
 * @Description 数组对象大小测试
 * @Author houzw
 * @Date 2020/7/8
 **/
public class ArrayObject {
    public static void main(String[] args) {
        int[] arrays = {1,2,3};
        System.out.println(ClassLayout.parseInstance(arrays).toPrintable());
    }
}
//    ==默认开启指针压缩:-XX:+UseCompressedOops==
//[I object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0      4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4      4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8      4        (object header)                           6d 01 00 f8 (01101101 00000001 00000000 11111000) (-134217363)
//        12     4        (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
//        16    12    int [I.<elements>                             N/A
//        28     4        (loss due to the next object alignment)
//        Instance size: 32 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

//    ==关闭指针压缩：-XX:-UseCompressedOops==
//[I object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0      4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4      4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8      4        (object header)                           68 db 03 ae (01101000 11011011 00000011 10101110) (-1375478936)
//        12     4        (object header)                           1d 7f 00 00 (00011101 01111111 00000000 00000000) (32541)
//        16     4        (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
//        20     4        (alignment/padding gap)
//        24    12    int [I.<elements>                             N/A
//        36     4        (loss due to the next object alignment)
//        Instance size: 40 bytes
//        Space losses: 4 bytes internal + 4 bytes external = 8 bytes total

