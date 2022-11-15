package com.hzw.learn.reactor;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @ClassName BaseLearn
 * @Description TODO
 * @Author houzw
 * @Date 2022/11/9
 **/
public class ReactorLearn {

    Logger log = LoggerFactory.getLogger(getClass());

    /*
    * 1. 生成数据流
    * 2. 数据流订阅
    */
    @Test
    public void test1(){
        log.info("=========");
        // 1. 生成数据流
        // Flux，能够产生0~N个元素的数据流
        Flux<Integer> just_flux = Flux.just(1, 2, 3, 4);
        // Mono，能够产生0~1个元素
        Mono<Integer> just_mono = Mono.just(1);

        // 2. 数据流订阅 只有订阅了数据才会开始流动
        just_flux.log().subscribe(System.out::println);
        just_mono.log().subscribe(System.out::println);
        // 上面subscribe方法的参数最后被组装成LambdaMonoSubscriber,我们也可以自定义Subscriber
        // 下面反压案例就是自定义了一个Subscriber
    }

    /*
     * 3. 反压力(反压)
     */
    @Test
    public void test2(){
        // 3. 反压力(反压)
        Flux<Integer> just_flux = Flux.just(5, 6, 9, 7);
        just_flux.log().subscribe(new Subscriber<Integer>() {
            Subscription s;
            @Override
            public void onSubscribe(Subscription s) {
                log.info("=onSubscribe:{}",s);
                this.s=s;
                s.request(2);
                // 通过request()方法告诉生产者每次推送多少个元素
                // 订阅者通过这种方式控制上游下发的流量
            }
            @Override
            public void onNext(Integer integer) {
                log.info("=onNext:{}",integer);
                sleep(1000);
                s.request(1);
            }
            @Override
            public void onError(Throwable t) {
                log.info("=onError:{}",t);
            }
            @Override
            public void onComplete() {
                log.info("=onComplete");
            }
        });
    }

    /*
     * 4. 映射数据
     */
    @Test
    public void test3(){
        Flux.just(1, 2, 3, 4).log()
            .map(i -> i*10)
            .subscribe(System.out::println);
    }





    public static void sleep(long ms){
        try {Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
