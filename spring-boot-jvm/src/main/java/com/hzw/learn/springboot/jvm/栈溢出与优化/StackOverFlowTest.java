package com.hzw.learn.springboot.jvm.栈溢出与优化;

/**
 * @ClassName StackOverFlowTest
 * @Description TODO
 * @Author houzw
 * @Date 2020/7/9
 **/
public class StackOverFlowTest {
    private int val=0;
    public void test(){
        val++;
//        System.out.println("stack depth:" + val);
        test();
    }

    public static void main(String[] args) {
        StackOverFlowTest stackOverFlowTest = new StackOverFlowTest();
        try {
            stackOverFlowTest.test();
        }catch (Throwable t){
            System.out.println("stack depth " + stackOverFlowTest.val); // 这里输出的是栈深度
        }
    }
}

// 执行参数为 -Xss228k时    输出:"stack depth 1515"
// 栈溢出时的栈深度,和栈帧大小有关，栈帧大，栈能分的份数就约少

