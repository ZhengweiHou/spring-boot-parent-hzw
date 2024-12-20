package com.hzw.learn.kafkatest;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @ClassName kafkatest
 * @Description TODO
 * @Author houzw
 * @Date 2024/3/7
 **/
public class Kafkaconsume {

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "hzw_topic";

    @Test
    public void consumeMessages() throws InterruptedException {
        Properties props = new Properties();
        props.put("metadata.broker.list", BOOTSTRAP_SERVERS);
        props.put("group.id", "my_group");
        props.put("zookeeper.connect", ""); // 留空以避免使用 ZooKeeper
        props.put("auto.offset.reset", "smallest"); // 从分区的起始位置开始消费
        props.put("consumer.timeout.ms", "1000"); // 设置超时时间

        ConsumerConfig consumerconfig = new ConsumerConfig(props);
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(consumerconfig);

        Map<String, Integer> topicCountMap = new HashMap<String,Integer>();
        topicCountMap.put(TOPIC + ":" + 0, 1); // 指定要消费的主题和分区

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(TOPIC + ":" + 0);

        for (final KafkaStream<byte[], byte[]> stream : streams) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                String message = new String(it.next().message());
                System.out.println("Received: " + message);
            }
        }
    }

//    @Test
//    public void consumePartitionData() {
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my_group");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//
//        try (Consumer<String, String> consumer = new KafkaConsumer<String,String>(props)) {
//            TopicPartition topicPartition = new TopicPartition(TOPIC, 0);
//            consumer.assign(Arrays.asList(topicPartition));
//
//            while (true) {
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//                records.forEach(record -> {
//                    System.out.printf("Partition: %d, Offset: %d, Key: %s, Value: %s%n",
//                            record.partition(), record.offset(), record.key(), record.value());
//                });
//            }
//        }
//    }

}
