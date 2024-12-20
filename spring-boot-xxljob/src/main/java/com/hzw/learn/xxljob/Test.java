package com.hzw.learn.xxljob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

/**
 * @ClassName Test
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/3
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        User user = new User("hzw", 12);

        ByteArrayOutputStream bs = new ByteArrayOutputStream(512);
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(user);

        byte[] bt = bs.toByteArray();

        System.out.println(bt.length);

        String encoded = Base64.getEncoder().encodeToString(bt);
        System.out.println(encoded);

        System.out.println(Arrays.toString(bt));

    }
}

class User implements Serializable {
    String name;
    Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
