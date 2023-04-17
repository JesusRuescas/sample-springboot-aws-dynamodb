package com.dynamodb.aws.springboot.springbootawsdynamodb.config;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import com.dynamodb.aws.springboot.springbootawsdynamodb.model.MetricsModel;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { MetricsModel.class })
public class DynamoDbConfiguration {
	@Value("${cloud.aws.region.static}")
	private String region;
	private final static Logger LOGGER = LoggerFactory.getLogger(DynamoDbConfiguration.class);

	public DynamoDbConfiguration() {
		LOGGER.trace("Joining DynamoDB Configuration");
	}

	@Bean
	public DynamoDbClient dynamoDbClient(AwsCredentialsProvider awsCredentialsProvider,
			@Value("${application.dynamodb.endpoint:}") String endpoint) {
		DynamoDbClientBuilder clientBuilder = DynamoDbClient.builder().credentialsProvider(awsCredentialsProvider)
				.region(Region.of(region));
		if (StringUtils.hasLength(endpoint)) {
			clientBuilder.endpointOverride(URI.create(endpoint));
		}
		return clientBuilder.build();
	}

	@Bean
	@Primary
	public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
		return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
	}

}
