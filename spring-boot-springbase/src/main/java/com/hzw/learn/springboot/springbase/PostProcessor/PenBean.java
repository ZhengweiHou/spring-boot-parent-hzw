package com.hzw.learn.springboot.springbase.PostProcessor;

/**
 * @ClassName HelloImpl
 **/
public class PenBean{

    public int pen;

    public PenBean() {
    }

    public PenBean(int msg) {
        this.pen = msg;
    }

    public int getPen() {
        return pen;
    }

    public void setPen(int pen) {
        this.pen = pen;
    }

    public void sayHello() {
        System.out.println(pen);
    }


}
