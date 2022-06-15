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

# masterdevProject1
## Bai4_Kafka
###  "src\main\java\org\cuminhtien\kafka\AppConfigs.java":
if you want to change topic and server
```
    public final static String bootstrapServers = "172.17.80.20:9092";
    public final static String topicName = "customer_tiencm8";
```
