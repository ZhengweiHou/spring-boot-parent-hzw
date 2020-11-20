package com.hzw.learn.springboot.javabase.StringEscape;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringEscapeUtilsTest {

    // JDBC4PreparedStatement中精简出来的一段方法

    @Test
    public void isEscapeNeededForStringTest(){
        String str1 = "abcdef";
        Assert.assertFalse(StringEscapeUtils.isEscapeNeededForString(str1));

        String str2 = "ab'cdef";
        Assert.assertTrue(StringEscapeUtils.isEscapeNeededForString(str2));

        String str3 = "ab\rcdef";
        Assert.assertTrue(StringEscapeUtils.isEscapeNeededForString(str3));
    }

    @Test
    public void escapeStringTest() throws UnsupportedEncodingException {
        String str1 = "a'b'c哈达放假    '阿斯顿分\n\"";

        System.out.println(StringEscapeUtils.escapeString(str1,"UTF-8"));



    }

}
