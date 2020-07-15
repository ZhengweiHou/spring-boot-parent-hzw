package test;

import java.lang.reflect.InvocationTargetException;

/**
 * @ClassName Test
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/15
 **/
public class Test{
    @org.junit.Test
    public void test() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        A a = A.class.newInstance();
        a.t = new B();
        System.out.println(a.t.getClass());


        A hehe = A.class.getConstructor(String.class).newInstance("hehe");
        hehe.t = new String();
        System.out.println(hehe.t.getClass());


    }
}
