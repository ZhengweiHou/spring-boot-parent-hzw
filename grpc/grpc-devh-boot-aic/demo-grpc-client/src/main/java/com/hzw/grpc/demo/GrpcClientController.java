/*
 * Copyright (c) 2016-2021 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.hzw.grpc.demo;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.client.AicGrpc;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Optional demo controller making the grpc service accessible via browser requests.
 */
@RestController
@GrpcAdvice
public class GrpcClientController {

    @AicGrpc(value = "ccc",serializer = "jdk")
    private HzwApi hzwApi_inject_by_BPP;

    @AicGrpc(value = "ccc")
    private HzwApi hzwApi_proto;

    @AicGrpc(value = "ccc",serializer = "jdk")
    private HzwApi hzwApi_jdk;

    @Autowired
    @Qualifier("hzwApi")
    private HzwApi hzwApiProto;

    @Autowired
    @Qualifier("hzwApiJdk")
    private HzwApi hzwApiJdk;

    @Autowired
    @Qualifier("hzwApi_byxml")
    private HzwApi hzwApi_byxml;


    @RequestMapping("/1")
    public String printMessage(@RequestParam(defaultValue = "sirius") final String name) {
        try {
            return hzwApi_inject_by_BPP.sayHello(name);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/2")
    public String printMessage2(@RequestParam(defaultValue = "sirius") final String name) {
        try {
            return hzwApiProto.sayHello(name);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/3")
    public void printMessage3(@RequestParam(defaultValue = "sirius") final String name) {
        hzwApiProto.voidHello(name);
    }

    @RequestMapping("/4")
    public void printMessage4(@RequestParam(defaultValue = "sirius4") final String name) {
        hzwApi_byxml.sayHello(name);
    }

    @RequestMapping("/5")
    public String printMessage5(@RequestParam(defaultValue = "sirius5") final String name) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08");
        return hzwApi_byxml.sayHello(name, date);
    }

    @RequestMapping("/6")
    public int printMessage6() throws ParseException {
        return hzwApiProto.sayHello(11);
    }
    @RequestMapping("/7")
    public boolean p7() throws ParseException {
        return hzwApiProto.sayHello(true);
    }

    @RequestMapping("/nullarg1")
    public String nullarg1() {
        return hzwApi_byxml.sayHello(null,15);
    }

    @RequestMapping("/nullarg2")
    public String nullarg2() {
        return hzwApiJdk.sayHello(null,15);
    }

    @RequestMapping("/nullarg3")
    public String nullarg3() {
        return hzwApi_byxml.sayHello("",15);
    }

    @RequestMapping("/testp")
    public String testp() throws ParseException {

        String a = hzwApi_proto.sayHello("test1");
        assert "str:test1".equals(a);
        int b = hzwApi_proto.sayHello(1);
        assert b == 10;
        boolean c = hzwApi_proto.sayHello(true);
        assert !c;
        Integer d = hzwApi_proto.sayHello(new Integer(2));
        assert d == 20;
        String e = hzwApi_proto.sayHello("test2", 3);
        assert "str:test2,int:3".equals(e);
        String e2 = hzwApi_proto.sayHello(null, 3);
        assert "str:null,int:3".equals(e);

        HashMap map1 = hzwApi_proto.sayMap(null);

        String f = hzwApi_proto.sayHello("test3", new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08"));
        assert "str:test3,date:2000-08-08".equals(f);
        hzwApi_proto.voidHello("test4");
        try {
            hzwApi_proto.exceptionHello("test5");
        }catch (RuntimeException error){
            System.out.println(error.getMessage());
        }

        return "hello";
    }

    @RequestMapping("/testj")
    public String testj() throws ParseException {

        String a = hzwApi_jdk.sayHello("test1");
        assert "str:test1".equals(a);
        int b = hzwApi_jdk.sayHello(1);
        assert b == 10;
        boolean c = hzwApi_jdk.sayHello(true);
        assert !c;
        Integer d = hzwApi_jdk.sayHello(new Integer(2));
        assert d == 20;
        String e = hzwApi_jdk.sayHello("test2", 3);
        assert "str:test2,int:3".equals(e);
        String f = hzwApi_jdk.sayHello("test3", new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08"));
        assert "str:test3,date:2000-08-08".equals(f);
        hzwApi_jdk.voidHello("test4");
        try {
            hzwApi_jdk.exceptionHello("test5");
        }catch (RuntimeException error){
            System.out.println(error.getMessage());
        }

        return "hello";
    }
}
