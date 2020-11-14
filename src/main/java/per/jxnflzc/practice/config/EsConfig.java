package per.jxnflzc.practice.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "per.jxnflzc.practice.repositories")
public class EsConfig {

    @Bean
    RestHighLevelClient elasticsearchClient() {
        ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .build();
        RestHighLevelClient client = RestClients.create(configuration).rest();

        return client;
    }

}
