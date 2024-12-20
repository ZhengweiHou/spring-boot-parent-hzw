package com.hzw.learn.kafkatest;

import kafka.consumer.ConsumerConnector;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.consumer.RoundRobinAssignor;
import kafka.producer.KeyedMessage;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.*;

/**
 * @ClassName kafkatest
 * @Description TODO
 * @Author houzw
 * @Date 2024/3/7
 **/
public class Kafkatest {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "hzw_topic";

    public static void main(String[] args) throws InterruptedException {
        // 生产者示例
        produceMessage("Hello, Kafka!");
    }

    private static void produceMessage(String message) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("metadata.broker.list", BOOTSTRAP_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partition.assignment.strategy", RoundRobinAssignor.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);

//        KeyedMessage<String, String> data = new KeyedMessage<String,String>(TOPIC, message);
        ProducerRecord<String, String> data = new ProducerRecord<String, String>(TOPIC, message);
        producer.send(data);
        producer.close();
        System.out.println("Message sent successfully.");
    }
}
