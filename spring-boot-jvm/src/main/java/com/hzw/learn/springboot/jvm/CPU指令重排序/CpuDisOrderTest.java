package com.hzw.learn.springboot.jvm.CPU指令重排序;

/**
 * @ClassName CpuDisOrderTestment
 * @Description CPU指令重排序演示
 * @Author houzw
 * @Date 2020/7/13
 **/
public class CpuDisOrderTest {
    public static int x=0, y=0;
    public static int a=0, b=0;

    public static void main(String[] args) throws InterruptedException {
        
        long currentTime = System.currentTimeMillis();
        for(int i=0;;i++){

            x=-1;
            y=-1;
//            a=b=0;
            a=0;
            b=0;

            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    x = a;
                }
            });

            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a=1;
                    y=b;
                }
            });
            thread1.start();
            thread2.start();

            thread1.join(); // main线程等thread1执行完
            thread2.join(); // main线程等thread2执行完

            if(x==0 && y==0){
                System.out.println("执行" + i + "次，x=" + x + ",y=" + y);
                break;
            }
        }
        
        System.out.println("耗时：" + (System.currentTimeMillis() - currentTime) +"ms");
    }
}

//  执行27894次，x=0,y=0
//  耗时：3910ms
