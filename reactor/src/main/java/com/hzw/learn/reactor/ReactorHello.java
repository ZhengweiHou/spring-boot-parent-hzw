package com.hzw.learn.reactor;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @ClassName ReactorTest1
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/2
 **/

@FixMethodOrder(MethodSorters.JVM)
public class ReactorHello {

    @Test
    public void reactorTest_1() throws InterruptedException {
        // 创建一个Flux对象，它代表一个响应式流
//        Flux<String> numbersFlux = Flux.range(1, 10)
//                .map(i -> "number: " + i);
//                .log(); // 添加日志处理

        Flux<Integer> numbersFlux = Flux.just(1, 2, 3);

        // 订阅并处理流中的数据
        Disposable a = numbersFlux.subscribe(
                System.out::println, // 数据处理
                System.err::println, // 错误处理
                () -> System.out.println("Stream completed.") // 完成处理
        );

        Disposable b = numbersFlux.subscribe(
                System.out::println, // 数据处理
                System.err::println, // 错误处理
                () -> System.out.println("Stream completed.") // 完成处理
        );

        System.out.println("===============");


        Flux<String> flux2 = Flux.just("1", "2");

//       a.dispose();

        // 在另一个线程上调度和执行操作
//        Flux.range(1, 10)
//                .subscribeOn(Schedulers.parallel())
//                .subscribe(
//                        System.out::println,
//                        System.err::println,
//                        () -> System.out.println("Parallel stream completed.")
//                );
    }
}
