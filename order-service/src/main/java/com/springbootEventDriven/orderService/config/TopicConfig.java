package com.springbootEventDriven.orderService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Value( "${spring.kafka.producer.topic-name}" )
    private String topicname;

    @Bean
    public NewTopic createTopic(){
        return TopicBuilder.name(topicname)
                .build();
    }

    // spring bean for kafka topic -
    // @Configuration @Bean - spring will recognise it as a spring bean automatic create a bean
    // for this class and store it in ios container

}
