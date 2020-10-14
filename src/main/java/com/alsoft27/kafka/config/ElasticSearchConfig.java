package com.alsoft27.kafka.config;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.MainResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

	private static final Logger log = LoggerFactory.getLogger(ElasticSearchConfig.class);

	@Bean(destroyMethod = "close")
	public RestHighLevelClient createClient() throws IOException {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("alsoft27", "+++++++"));

		RestHighLevelClient client = new RestHighLevelClient(RestClient
				.builder(new HttpHost("search-alsoft27-yzxsilpxyqnuiqdo2pnpdwredm.eu-west-1.es.amazonaws.com", 443,
						"https"))
				.setHttpClientConfigCallback((config) -> config.setDefaultCredentialsProvider(credentialsProvider)));

		MainResponse response = client.info(RequestOptions.DEFAULT);
		String clusterUuid = response.getClusterUuid();
		String nodeName = response.getNodeName();
		log.info("Información del cluster: ");
		log.info("Nombre del cluster: {}", response.getClusterName());
		log.info("Identificador del cluster: {}", clusterUuid);
		log.info("Nombre de los nodos del cluster: {}", nodeName);

		return client;
	}

}
