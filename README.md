# masterdevProject1
## Bai2_Spring
###  "src/main/resources/application.properties":
```
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url = jdbc:mysql://localhost:3306/{DATABASE}?useSSL=false
  spring.datasource.username = {USERNAME}
  spring.datasource.password = {PASSWORD}
  #spring.jpa.show-sql: true
  spring.security.user.name=admin
  spring.security.user.password=admin
  spring.security.user.roles=admin
```
## Bai4_Kafka
###  "src\main\java\org\cuminhtien\kafka\AppConfigs.java":
if you want to change topic and server
```
    public final static String bootstrapServers = "172.17.80.20:9092";
    public final static String topicName = "customer_tiencm8";
```
## Bai7_Hadoop_MapReduce
###  "src\main\java\org\cuminhtien\bai1\WordCount2":
Run on IntelliJ - Edit Configurations - Build and run
```
input\input.txt output -skip patterns.txt
```
###  "src\main\java\org\cuminhtien\bai2\CountDistinct":
Run on IntelliJ - Edit Configurations - Build and run
```
count_distinct.csv output2 output3
```
###  "src\main\java\org\cuminhtien\bai3\JoinTable":
Run on IntelliJ - Edit Configurations - Build and run
```
people.csv salary.csv output4
```
#### Result on Server "/tiencm8/bai1/output1"
#### Result on Server "/tiencm8/bai2/output1" , "/tiencm8/bai2/output2"
#### Result on Server "/tiencm8/bai3/output1"
## Bai8_Kafka
+ Build file jar của project: sử dụng lệnh mvn package
+ scp file jar của project lên server (ví dụ : scp spark_java-1.0-SNAPSHOT.jar hadoop@172.17.80.21:/home/hadoop/tiencm8)
+ Chạy spark_java_bigger : spark-submit --deploy-mode cluster --master yarn --class spark_java_bigger spark_java-1.0-SNAPSHOT.jar (Spark chạy với Yarn ở server 172.17.80.21 - password : 1)
+ Chạy spark_java_smaller : spark-submit --deploy-mode cluster --master yarn --class spark_java_smaller spark_java-1.0-SNAPSHOT.jar
#### Output được save trên HDFS :
+ hdfs://172.17.80.21:9000/tiencm8/spark/bigger/*
+ hdfs://172.17.80.21:9000/tiencm8/spark/smaller/*
## Bai9_Structured Streaming
+ Build file jar của project: sử dụng lệnh mvn package
+ scp file jar của project lên server (ví dụ : scp -r Spark_Streaming-1.0-SNAPSHOT.jar hadoop@172.17.80.21:/home/hadoop/tiencm8)
+ Chạy spark-submit: trên server `172.17.80.21` trong folder `/home/hadoop/tiencm8` : spark-submit --deploy-mode cluster --packages org.apache.spark:spark-sql-kafka-0-10_2.11:2.4.0,com.google.protobuf:protobuf-java:3.12.2 --class StructuredStreaming Spark_Streaming-1.0-SNAPSHOT.jar
#### Checkpoint :
+ hdfs://172.17.80.21:9000/user/tiencm8/data_tracking/checkpoint
#### Output :
+ hdfs://172.17.80.21:9000/user/tiencm8/data_tracking
#### Câu lệnh tạo bảng Hive trên server `172.17.80.21`:
    
    CREATE EXTERNAL TABLE data_traking_tiencm8_2015_07_22(version string, name string, `timestamp` string, phone_id string, lon string, lat string) PARTITIONED BY (year int, day int, hour int) STORED AS PARQUET LOCATION '/user/tiencm8/data_tracking/data/year=2015/month=07/day=22';

#### Câu lệnh đồng bộ dữ liệu trên hdfs với Hive metastore

    MSCK REPAIR TABLE data_traking_tiencm8;
####  `SELECT COUNT(*) FROM data_traking_tiencm8_2015_07_22;

