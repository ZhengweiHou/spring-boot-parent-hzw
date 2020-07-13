package com.hzw.learn.springboot.jvm.Volatile;

/**
 * @ClassName VolatileTest2
 * @Description volatile无法保证原子性 测试
 * @Author houzw
 * @Date 2020/7/9
 **/
public class VolatileTest2 {
    public static volatile int count = 0;

    public static void main(String[] args) {
        for (int i = 0 ; i<3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int flag = 0;
                    while (flag < 10000) {
                        add();
                        flag++;
                    }
                }
            }, "thread" + i).start();
        }

        while (Thread.activeCount() > 2){}

        System.out.println(count); // 结果是否符合预期
    }
    
    public static void add(){
        count++;
    }
}


