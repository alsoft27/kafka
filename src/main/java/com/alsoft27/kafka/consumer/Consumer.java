package com.alsoft27.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alsoft27.kafka.message.SampleMessage;

@Component
public class Consumer {

	private static final Logger log = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private RestHighLevelClient client;

	@KafkaListener(topics = "${kafka-topic}", groupId = "${kafka-consumers}", containerFactory = "kafkaListenerContainerFactory")
	public void processMessage(ConsumerRecord<String, SampleMessage> message) {
		// log.info("Message received {}, partition = {}, offset = {}, key = {} ",
		// message.value(), message.partition(), message.offset(), message.key());
		IndexRequest indexRequest = buildIndexRequest(
				String.format("%s-%s-%s", message.partition(), message.key(), message.offset()), message.value());
		client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {

			@Override
			public void onResponse(IndexResponse response) {
				log.debug("Successfull request");

			}

			@Override
			public void onFailure(Exception e) {
				log.error("Error storing the request {}", e);
			}
		});
	}

	private IndexRequest buildIndexRequest(String key, SampleMessage value) {
		IndexRequest request = new IndexRequest("alsoft27-messages");
		request.id(key);
		request.source(value, XContentType.JSON);
		return request;
	}
}
