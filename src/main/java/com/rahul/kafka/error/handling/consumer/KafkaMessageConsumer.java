package com.rahul.kafka.error.handling.consumer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.kafka.error.handling.dto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageConsumer {

	@RetryableTopic(attempts ="4")
	@KafkaListner(topics= ${app.comm.rahul",groupId=""})
	public void consumerEvents(User user , @Header(KafkaHeaders.RECEIVED_TOPIC) String topic ,
			@Header(KafkaHeaders.OFFSET) long offset
			) {
		try {
			log.info("Received :{}  from {} offset {}",new ObjectMapper().writeValueAsString(user) ,topic , offset);
			
			List<String> restrictedIpList = Stream.of("192.168.102.1","","").collect(Collectors.toList());
			
			if(restrictedIpList.contains(user.getIpAddress())) {
				throw new RuntimeException("Invalid IP address received");
			}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void listenDLT(User user , @Header(KafkaHeaders.RECEIVED_TOPIC) String topic ,
			@Header(KafkaHeaders.OFFSET){
		
	}
}
