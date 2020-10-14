package com.alsoft27.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alsoft27.kafka.message.SampleMessage;
import com.alsoft27.kafka.producer.Producer;
import com.github.javafaker.Faker;

@SpringBootApplication
public class KafkaAlsoft27Application {

	private static final Logger log = LoggerFactory.getLogger(KafkaAlsoft27Application.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaAlsoft27Application.class, args);
	}

	@Bean
	public ApplicationRunner runner(Producer producer) {
		return (args) -> {
			long startTime = System.currentTimeMillis();
			for (int i = 1; i < 1000; i++) {
				Faker faker = new Faker();
				String name = faker.name().firstName();
				String lastname = faker.name().lastName();
				double cant = faker.number().randomDouble(2, 1, 2000000);
				producer.send(new SampleMessage(i, name, lastname, cant));
			}
			log.info("Processing time = {}", System.currentTimeMillis() - startTime);
		};
	}

}
