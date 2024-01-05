package com.hzw.learn.ext;

/**
 * @ClassName HzwException
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/23
 **/
public class HzwException extends Exception{
    public HzwException(String msg) {
        super(msg);
        this.msg = msg;
    }

    String msg;

    public String getMsg(){
        return msg;
    }
}
