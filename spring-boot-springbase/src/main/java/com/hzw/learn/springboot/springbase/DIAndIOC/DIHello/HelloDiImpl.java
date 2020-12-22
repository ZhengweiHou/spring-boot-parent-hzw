package com.hzw.learn.springboot.springbase.DIAndIOC.DIHello;

/**
 * @ClassName HelloDiImpl
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/1
 **/
public class HelloDiImpl implements HelloDIapi
{
    private int age;
    private String name;
    private boolean  goodPerson=true;

    // 公共的无参构造器
    public HelloDiImpl(){}

   /*
    构造器注入可以根据参数索引注入、参数类型注入或Spring3支持的参数名注入，
    但参数名注入是有限制的，需要使用在编译程序时打开调试模式（即在编译时使用
    “javac –g:vars”在class文件中生成变量调试信息，默认是不包含变量调试信息
    的，从而能获取参数名字，否则获取不到参数名字）
    或在构造器上使用@ConstructorProperties（java.beans.ConstructorProperties）
    注解来指定参数名
    另外注意@ConstructorProperties注解对工厂方式通过参数名注入不起作用
    */
   // @ConstructorProperties({"age","name"})
    public HelloDiImpl(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public void sayHello() {
        System.out.println("大家好，我是" + name + ",今年" + age + "岁了！ " +
                "我" + (goodPerson ? "是":"不是") +"个好人");
    }

    /*setter*/
    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGoodPerson(boolean goodPerson) {
        this.goodPerson = goodPerson;
    }
}
