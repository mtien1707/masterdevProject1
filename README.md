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
# masterdevProject1
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
###  "src\main\java\org\cuminhtien\bai2\JoinTable":
Run on IntelliJ - Edit Configurations - Build and run
```
people.csv salary.csv output4
```

