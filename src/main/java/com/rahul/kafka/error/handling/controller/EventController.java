package com.rahul.kafka.error.handling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.kafka.error.handling.dto.User;
import com.rahul.kafka.error.handling.publisher.KafkaMessafePublisher;

@RestController
public class EventController {

	@Autowired
	private KafkaMessafePublisher kafkaMessafePublisher;
	
	@PostMapping("/publishNew")
	public ResponseEntity<?> publishEvent(@RequestBody User user){
		try {
			kafkaMessafePublisher.sendEvents(user);
			return ResponseEntity.ok("Message published successfully");
		}catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	/*
	 "id":1,
	 "firstName":"",
	 "lastName":"",
	 "email":"",
	 "gender":"",
	 "ipAddress":""
	 */
}
