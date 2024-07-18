package com.rahul.kafka.error.handling.publisher;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import com.rahul.kafka.error.handling.dto.User;

public class KafkaMessafePublisher {

	@Value("${app.topic.name}")
	private String topicName;
	
	@Autowired
	private KafkaTemplate kafkaTemplate;
	
	public void sendEvents(User user) {
		try {
			CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(topicName,user);
			future.whenComplete((result,ex) ->{
				
				if(ex == null) {
					System.out.println("Sent message ={" + user.toString() +
							"} with offset = {" + result.getRecordMetadata().offset() + "}"
							);
				}else {
					System.out.println("Unable to send message { " +
							user.toString() + "} due to " + ex.getMessage()
							);
				}
			});
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			// TODO: handle exception
		}
	}
	
}
