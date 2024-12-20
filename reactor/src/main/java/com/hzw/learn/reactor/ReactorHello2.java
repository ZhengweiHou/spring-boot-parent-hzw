package com.hzw.learn.reactor;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @ClassName ReactorTest1
 * @Description TODO
 * @Author houzw
 * @Date 2024/7/2
 **/

@FixMethodOrder(MethodSorters.JVM)
public class ReactorHello2 {

    @Test
    public void reactorTest_1() throws InterruptedException {

        Flux<Integer> numbersFlux = Flux.just(1, 2, 3);
        Mono<String> mono = Mono.defer(() -> {
            numbersFlux.subscribe(System.out::println);
            return Mono.just("123");
        });

        System.out.println("===============");

        mono.subscribe(System.out::println) ;


        Flux<Integer> flux2 = Flux.just(1,2,3,1,1,1,1,2,5);

        Mono<String> mon2 = Mono.defer(new Supplier<Mono<String>>() {
            @Override
            public Mono<String> get() {
                Mono<String> fluxv = flux2.count().map(v -> {
                    return "flux中元素的个数:" + v;
                });

                return fluxv;
//                return Mono.just("123");
            }
        });

        mon2.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
