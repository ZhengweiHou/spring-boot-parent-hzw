package test;

import org.apache.dubbo.rpc.proxy.InvokerInvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        ArrayList<B> objects = new ArrayList<>();
        for (int i=0; i < 1*100*100*100; i++){
            System.out.println(i);
//            objects.add(new B());
            new B();
        }

        while (true){

        }
    }

    @org.junit.Test
    public void test1(){
//        InvokerInvocationHandler
        /**
         * @see InvokerInvocationHandler#invoke(Object, Method, Object[])
         */

    }

}
