package cn.guxiangfly.kafkalearn.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author guxiang02
 * @Date 2021/4/8
 **/
public class KafkaProducerTest {
    private final static String TOPIC_NAME="guxiang-topic";

    /*Producer异步发送演示*/
    public static void producerSend(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.220.128:9092");
        properties.put(ProducerConfig.ACKS_CONFIG,"all");
        properties.put(ProducerConfig.RETRIES_CONFIG,"0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"16384");
        properties.put(ProducerConfig.LINGER_MS_CONFIG,"1");
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,"33554432");

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // Producer的主对象
        Producer<String,String> producer = new KafkaProducer<>(properties);

        // 消息对象 - ProducerRecoder
        for(int i=0;i<10;i++){
            ProducerRecord<String,String> record =
                    new ProducerRecord<>(TOPIC_NAME,"key-"+i,"value-"+i);

            producer.send(record);
        }
        // 所有的通道打开都需要关闭
        producer.close();
    }
}
