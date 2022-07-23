import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

public class ConsumerKafka {
    public static void main(String[] str) throws InterruptedException, IOException, TimeoutException {


        Properties props = new Properties();


        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "tiencm8");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.put("schema.registry.url", "http://172.17.80.26:8081");
        props.put("enable.auto.commit", "true");
        props.put("auto.offset.reset", "earliest");
        props.put("auto.commit.interval.ms", "100");
        props.put("heartbeat.interval.ms", "3000");
        props.put("session.timeout.ms", "30000");
        String topic = "data_tracking_tiencm8";

        KafkaConsumer<String, byte[]> consumer = new KafkaConsumer<String, byte[]>(props);

        // Assign to specific topic and partition, subscribe could be used here to subscribe to all topic.
        consumer.assign(Arrays.asList(new TopicPartition(topic, 0)));

        while (true) {
            ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, byte[]> record : records) {
                 DataTrackingProto.DataTracking dttk = DataTrackingProto.DataTracking.parseFrom(record.value());
                System.out.printf("\n\roffset = %d, key = %s, value = %s", record.offset(), record.key(), dttk);
            }


            consumer.commitSync();
            Thread.sleep(100);

        }
    }
}
