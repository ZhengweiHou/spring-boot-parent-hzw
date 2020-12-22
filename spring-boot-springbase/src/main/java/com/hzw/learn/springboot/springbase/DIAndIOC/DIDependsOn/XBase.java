package com.hzw.learn.springboot.springbase.DIAndIOC.DIDependsOn;

/**
 * @ClassName XBase
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/3
 **/
public abstract class XBase {

    public void pinit(){
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ": init !!");
    }

    public void pdestory(){
        System.out.println(this.hashCode() + "=" + this.getClass().getSimpleName() + ": destory !!");
    }
}
