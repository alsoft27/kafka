package com.alsoft27.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alsoft27.kafka.message.SampleMessage;

@Component
public class Producer {

	private static final Logger log = LoggerFactory.getLogger(Producer.class);

	@Autowired
	private KafkaTemplate<String, SampleMessage> kafkaTemplate;

	@Value("${kafka-topic}")
	private String topic;

	Producer(KafkaTemplate<String, SampleMessage> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(SampleMessage message) {
		this.kafkaTemplate.send(topic, message);
		log.info("Sent sample message {} to ", message);
	}
}
