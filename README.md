# spring-boot-example

## starting application

### mysql database and tables
```sql
create database learning;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `age` int unsigned NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `weight` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `age` (`age`),
  KEY `idx_name_age` (`name`(6),`age`),
  KEY `idx_age_weight` (`age`,`weight`)
) ENGINE=InnoDB AUTO_INCREMENT=1007766 DEFAULT CHARSET=utf8mb3

CREATE TABLE `account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `balance` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

### procedure

1. finish configuration of redis, zookeeper and mysql
2. mvn package -Dmaven.test.skip
3. java -jar spring-cloud-eureka-server-1.0-SNAPSHOT.jar
4. java -jar  -Dserver.port=9090 -Ddubbo.protocol.port=20880 springbootexample-server-1.0-SNAPSHOT.jar
5. java -jar  -Dserver.port=9091 -Ddubbo.protocol.port=20881  springbootexample-server-1.0-SNAPSHOT.jar
6. eureka: http://localhost:8900/
7. http: http://localhost:18080/users?page=3&size=10&routingKey=dubbo