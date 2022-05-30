package com.hzw.learn.ext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ClassName GsonTest
 * @Description TODO
 * @Author houzw
 * @Date 2022/5/30
 **/
public class GsonTest {
    public static void main(String[] args) {
        H h = new H();
        h.msg="msg";

        Z z = new Z();
        z.name="name";
        z.h=h;
        h.z=z;

//        System.out.println(new Gson().toJson(z));
        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(z));
    }
}
