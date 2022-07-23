import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class StructuredStreaming {
    public static void main(String[] args) throws StreamingQueryException, TimeoutException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Java Structured Streaming data from Kafka")
                .getOrCreate();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "172.17.80.26:9092")
                .option("subscribe", "data_tracking_tiencm8")
                .option("group.id","tiencm8")
                .option("startingOffsets", "earliest")
                .option("auto.offset.reset","true")
                .load();

        Dataset<byte[]> df1 = df.select("value").as(Encoders.BINARY());

        Dataset<String> df2 = df1.map((MapFunction<byte[], String>)
                        s ->  DataTrackingProto.DataTracking.parseFrom(s).getVersion() + ","
                                + DataTrackingProto.DataTracking.parseFrom(s).getName() + ","
                                + DataTrackingProto.DataTracking.parseFrom(s).getTimestamp() + ","
                                + DataTrackingProto.DataTracking.parseFrom(s).getPhoneId() + ","
                                + DataTrackingProto.DataTracking.parseFrom(s).getLon() + ","
                                + DataTrackingProto.DataTracking.parseFrom(s).getLat() + ","

                                + getTime(DataTrackingProto.DataTracking.parseFrom(s).getTimestamp(), "yyyy") + ","
                                + getTime(DataTrackingProto.DataTracking.parseFrom(s).getTimestamp(), "MM") + ","
                                + getTime(DataTrackingProto.DataTracking.parseFrom(s).getTimestamp(), "dd") + ","
                                + getTime(DataTrackingProto.DataTracking.parseFrom(s).getTimestamp(), "HH")
                , Encoders.STRING());

        Dataset<Row> df3 = df2.withColumn("version", functions.split(df2.col("value"), ",").getItem(0))
                .withColumn("name", functions.split(df2.col("value"), ",").getItem(1))
                .withColumn("timestamp", functions.split(df2.col("value"), ",").getItem(2))
                .withColumn("phoneId", functions.split(df2.col("value"), ",").getItem(3))
                .withColumn("lon", functions.split(df2.col("value"), ",").getItem(4))
                .withColumn("lat", functions.split(df2.col("value"), ",").getItem(5))
                .withColumn("year", functions.split(df2.col("value"), ",").getItem(6))
                .withColumn("month", functions.split(df2.col("value"), ",").getItem(7))
                .withColumn("day", functions.split(df2.col("value"), ",").getItem(8))
                .withColumn("hour", functions.split(df2.col("value"), ",").getItem(9))
                .drop("value");

        StreamingQuery query = df3
                .writeStream()
                .outputMode("append")
                .format("parquet")
                .option("checkpointLocation","hdfs://172.17.80.21:9000/user/tiencm8/data_tracking/checkpoint")
                .option("compression","snappy")
                .partitionBy("year", "month", "day", "hour")
                .option("path", "hdfs://172.17.80.21:9000/user/tiencm8/data_tracking/data")
                .start();

        query.awaitTermination();
        spark.streams().awaitAnyTermination(2000);
    }
    public static String getTime(long timestamp, String getBy){
        Date date = new java.util.Date(timestamp*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat(getBy);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+07:00"));
        String Formatted = sdf.format(date);
        return Formatted;
    }
}


