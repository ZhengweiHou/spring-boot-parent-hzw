package com.hzw.learn.springboot.springbase.Pattern.prototype;

/*
浅拷贝
*/
public class Prototype_simpleClone implements Cloneable {

    public HZWBean hzwBean = null;

    public Prototype_simpleClone(){
        hzwBean = new HZWBean();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
