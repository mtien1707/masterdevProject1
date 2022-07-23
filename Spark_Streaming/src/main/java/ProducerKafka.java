import org.apache.kafka.clients.producer.ProducerConfig;

import com.github.javafaker.Faker;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerKafka {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put("schema.registry.url", "http://172.17.80.26:8081");

        org.apache.kafka.clients.producer.Producer<String, byte[]> producer = new KafkaProducer(props);

        String topic = "data_tracking_tiencm8";

        int partition = 0;

        Faker faker = new Faker();

        for (int i = 0; i < 20; i++) {

            DataTrackingProto.DataTracking dataTrackingBuilder = DataTrackingProto.DataTracking.newBuilder()
                    .setVersion("version-" + faker.number().numberBetween(1, 9) + "." + faker.number().numberBetween(0, 20))
                    .setName(faker.name().fullName())
                    .setTimestamp(faker.number().numberBetween(1286705410, 1633860610))
                    .setPhoneId(faker.phoneNumber().phoneNumber())
                    .setLon(faker.number().numberBetween(0, 999))
                    .setLat(faker.number().numberBetween(0, 999))
                    .build();

            byte[] record = dataTrackingBuilder.toByteArray();

            producer.send(new ProducerRecord<String, byte[]>(topic, partition, Integer.toString(i), record));

            Thread.sleep(100);
        }

    }
}
