package com.hzw.learn.springboot.javabase.pattern;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    static ArrayList al =  new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {


//        String jvmArgs="-Dsystemtag=-hehe -DboltPort=12200";
        String jvmArgs="-Dsystemtag=-hehe -DboltPort=12200 -Dxxxxxx";
//        String jvmArgs="-Dsystemtag=-hehe  -Dxxxxxx";
        Pattern pattern = Pattern.compile(".*boltPort=([0-9]+).*");
        Matcher matcher = pattern.matcher(jvmArgs);
        if(matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(1));
        }
//        System.out.println(matcher.groupCount());



    }
}
