package com.rahul.kafka.error.handling.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

	@Value(value = "${app.topic.name}")
	private String topicname;
	
	
	@Bean
	public NewTopic createTopic() {
		return new NewTopic(topicname, 3, (short) 1);//
	}
	
}
