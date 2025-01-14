package com.hzw.learn.reactor;

import org.junit.Test;
import reactor.core.publisher.Mono;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TestDoFinally
 * @Description TODO
 * @Author houzw
 * @Date 2024/12/27
 **/
public class TestDoFinally {
    public static void main(String[] args) {
        Mono.just("hello")
                .doOnNext(v -> System.out.println("doOnNext:" + v))
                .doFinally(signalType -> System.out.println("doFinally signal:" + signalType))
                .subscribe(v -> System.out.println(v),
                        e -> System.out.println("Error:" + e),
                        () -> System.out.println("Completer")
                );
    }

    @Test
    public void t1(){
        Mono.just("hello")
                .doOnNext(v -> System.out.println("doOnNext:" + v))
                .doFinally(signalType -> System.out.println("doFinally signal:" + signalType))
                .subscribe(v -> System.out.println(v),
                        e -> System.out.println("Error:" + e),
                        () -> System.out.println("Completer")
                );
    }

    @Test
    public void t2() throws InterruptedException {
        Mono<Object> m = Mono.just("33a")
                .flatMap(v -> {
                    return Mono.create(sink -> {
                        new Thread(() -> {
                            int i = 0;
                            try {
                                i = Integer.parseInt(v);
                                sink.success("sink str to int:" + i);
                            } catch (Exception e) {
                                sink.error(e);
                            } finally {
                                System.out.println("finally1 " + new SimpleDateFormat("HHmmssSS").format(new Date()));
                            }
                        }).start();
                    });
                });
        m = m.doFinally(signalType -> {
            System.out.println("finally2 " + new SimpleDateFormat("HHmmssSS").format(new Date()));
            try {Thread.sleep(10);} catch (InterruptedException e) {}
        });

        m = m.doFinally(signalType -> {
            System.out.println("finally3 " + new SimpleDateFormat("HHmmssSS").format(new Date()));
            try {Thread.sleep(10);} catch (InterruptedException e) {}
        });

        m = m.doOnTerminate(() -> System.out.println("onTer1"))
                .doOnTerminate(() -> System.out.println("onTer2"))
                .doOnTerminate(() -> System.out.println("onTer3"));

        m.subscribe(v -> System.out.println("GetValue: " + v),
                e -> System.out.println("Error:" + e),
                () -> System.out.println("Completer")
        );

        Thread.sleep(100);


        try {
            try {
                int i = Integer.parseInt("21");
            } finally {
                System.out.println("111110");
            }
        }finally {
            System.out.println("2222221");
        }
    }

    @Test
    public void t3(){
        Mono.just("data")
                .doFinally(signalType -> System.out.println("doFinally 1"))
                .doFinally(signalType -> System.out.println("doFinally 2"))
                .doFinally(signalType -> System.out.println("doFinally 3"))
                .subscribe();

        Mono.just("data")
                .doOnTerminate(() -> Sout("onTer1"))
                .doOnTerminate(() -> Sout("onTer2"))
                .doOnTerminate(() -> Sout("onTer3"))
                .doFinally(signalType -> Sout("doFinally 1"))
                .doFinally(signalType -> Sout("doFinally 2"))
                .doFinally(signalType -> Sout("doFinally 3"))
                .subscribe();
        //=================================
        try{
            try {
                try {
                    int i = Integer.parseInt("21");
                } finally {
                    System.out.println("11111");
                }
            }finally {
                System.out.println("22222");
            }
        }finally {
            System.out.println("33333");
        }
    }

    public void Sout(String msg){
        String tname = Thread.currentThread().getName();
        System.out.println(tname + ": " + msg);
    }
}
