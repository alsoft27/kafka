package com.alsoft27.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alsoft27.kafka.message.SampleMessage;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "${kafka-topic}", groupId = "${kafka-consumers}", containerFactory = "kafkaListenerContainerFactory")
	public void processMessage(SampleMessage message) {
		log.info("Message received {} ", message);
	}
}
