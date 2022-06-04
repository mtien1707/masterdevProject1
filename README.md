# masterdevProject1



#bai2Spring
"src/main/resources/application.properties":
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url = jdbc:mysql://localhost:3306/{DATABASE}?useSSL=false
  spring.datasource.username = {USERNAME}
  spring.datasource.password = {PASSWORD}
  #spring.jpa.show-sql: true
  
  ## Security
  spring.security.user.name=admin
  spring.security.user.password=admin
  spring.security.user.roles=admin
