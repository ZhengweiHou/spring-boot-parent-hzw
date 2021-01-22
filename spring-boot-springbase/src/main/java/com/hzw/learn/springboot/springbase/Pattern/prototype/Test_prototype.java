package com.hzw.learn.springboot.springbase.Pattern.prototype;

import org.junit.Test;

public class Test_prototype {
    @Test
    public void simpleClone_test() throws CloneNotSupportedException {
        Prototype_simpleClone clone1 = new Prototype_simpleClone();
        Prototype_simpleClone clone2 = (Prototype_simpleClone) clone1.clone();

        System.out.println("clone1:" + clone1);
        System.out.println("clone2:" + clone2);
        System.out.println("clone1.hzwBean:" + clone1.hzwBean.hashCode());
        System.out.println("clone2.hzwBean:" + clone2.hzwBean.hashCode());
    }
/*
clone1:com.hzw.learn.springboot.springbase.Pattern.prototype.Prototype_simpleClone@3b764bce
clone2:com.hzw.learn.springboot.springbase.Pattern.prototype.Prototype_simpleClone@759ebb3d
clone1.hzwBean:1212899836
clone2.hzwBean:1212899836
*/

    @Test
    public void deepClone_test() throws CloneNotSupportedException {
        Prototype_deepClone clone1 = new Prototype_deepClone();
        Prototype_deepClone clone2 = (Prototype_deepClone) clone1.clone();

        System.out.println("clone1:" + clone1);
        System.out.println("clone2:" + clone2);
        System.out.println("clone1.hzwBean:" + clone1.hzwBean.hashCode());
        System.out.println("clone2.hzwBean:" + clone2.hzwBean.hashCode());
    }
/*
clone1:com.hzw.learn.springboot.springbase.Pattern.prototype.Prototype_deepClone@4cdf35a9
clone2:com.hzw.learn.springboot.springbase.Pattern.prototype.Prototype_deepClone@383534aa
clone1.hzwBean:1285044316
clone2.hzwBean:1807837413
*/
}
