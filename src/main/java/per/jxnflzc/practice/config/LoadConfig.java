package per.jxnflzc.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = { "classpath:properties/test.properties",
        "classpath:properties/sql.properties","classpath:properties/redis.properties"}, encoding = "utf-8")
public class LoadConfig {
}
