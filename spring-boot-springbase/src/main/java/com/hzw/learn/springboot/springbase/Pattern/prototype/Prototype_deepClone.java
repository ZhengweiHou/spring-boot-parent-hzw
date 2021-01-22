package com.hzw.learn.springboot.springbase.Pattern.prototype;

import java.io.*;
/*
* 使用序列化的方式，深拷贝对象
*/
public class Prototype_deepClone implements Cloneable {
    public HZWBean hzwBean = null;

    public Prototype_deepClone(){
        hzwBean = new HZWBean();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Prototype_deepClone obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            new ObjectOutputStream(bos).writeObject(this);

            ObjectInputStream os = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = (Prototype_deepClone) os.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
