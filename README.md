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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned NOT NULL,
  `balance` int NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `area` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `continent` varchar(64) NOT NULL,
    `country` varchar(64) NOT NULL,
    `province` varchar(64) NOT NULL,
    `population` bigint NOT NULL,
    `established_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ;

create table users
(
    `id`       int unsigned NOT NULL AUTO_INCREMENT,
    `username` varchar(50)  not null,
    `password` varchar(500) not null,
    `enabled`  tinyint      not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table authorities
(
    `id`        int unsigned NOT NULL AUTO_INCREMENT,
    `username`  varchar(50)  not null,
    `authority` varchar(50)  not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


```

### procedure

1. finish configuration of redis, zookeeper and mysql
2. mvn package -Dmaven.test.skip
3. java -jar -Xmx128m spring-cloud-eureka-server-1.0-SNAPSHOT.jar
4. java -jar -Xmx128m -Dserver.port=19090 -Ddubbo.protocol.port=20880 springbootexample-server-1.0-SNAPSHOT.jar
5. java -jar -Xmx128m -Dserver.port=19091 -Ddubbo.protocol.port=20881  springbootexample-server-1.0-SNAPSHOT.jar
6. eureka: http://localhost:8900/
7. http: http://localhost:18080/users?page=3&size=10&routingKey=dubbo