# PRACTICE README

[![Version](https://img.shields.io/badge/Version-1.3.1-brightgreen.svg)](https://github.com/jxnflzc/practice)
<br/>
[![License](https://img.shields.io/badge/License-GPLv3.0-blue)](https://github.com/jxnflzc/practice/blob/master/LICENSE)

### 部分配置文件

#### sql.properties

```properties
#数据库配置信息
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://{host}:{post}/{database}?serverTimezone=UTC
spring.datasource.username=username
spring.datasource.password=password

token.privateKey=privateKey
```

#### redis.properties

```properties
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host={host}
# Redis服务器连接端口
spring.redis.port={port}
# Redis服务器连接密码（默认为空）
spring.redis.password={password}
#连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=300
```

#### test.properties

```properties
#测试信息
test.info.name = {name}
test.info.version = ${practice.version}
```

