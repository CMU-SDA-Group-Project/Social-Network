package web.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;


@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.delayed-removal}")
    String delayedRemovalTopic;

    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic myTopic() {
        return new NewTopic(delayedRemovalTopic, 1, (short) 1);
    }

}
