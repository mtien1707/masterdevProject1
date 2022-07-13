import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class spark_java_bigger {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder().appName("spark_java_bigger").getOrCreate();
        Dataset<Row> smaller = spark.read().parquet("/chibm/spark/data/part1.snappy.parquet"); //file Name
        ((Dataset<?>) smaller).createOrReplaceTempView("data");
        Dataset<Row> countDF = spark.sql("select device_model, count(distinct user_id) as count from data WHERE device_model is not NULL group by device_model ");

        Dataset<Row> lstUserIdDF = spark.sql("select device_model,collect_set(user_id) as list_user_id from data group by device_model ");

        //bai3
        countDF.repartition(1).write().mode("overwrite").format("parquet").save("/tiencm8/spark/bigger/device_model_num_user");
        lstUserIdDF.repartition(1).write().mode("overwrite").format("orc").save("/tiencm8/spark/bigger/device_model_list_user");
        //bai4
        Dataset<Row> dataset1 = spark.sql("select * from data where device_model is not null");
        Dataset<Row> dataset2 = dataset1.withColumn("user_id_device_model", functions.array("user_id", "device_model"));
        ((Dataset<?>) dataset2).createOrReplaceTempView("data1");
        Dataset<Row> action_by_button_id = spark.sql("select user_id_device_model,button_id,count(*) as count from data1 where button_id is not null group by user_id_device_model,button_id having count(*)>=1");

        action_by_button_id.repartition(1).write().mode("overwrite").format("parquet").save("/tiencm8/spark/bigger/button_count_by_user_id_device_model");


    }
}
