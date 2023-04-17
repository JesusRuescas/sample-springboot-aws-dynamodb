package com.dynamodb.aws.springboot.springbootawsdynamodb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

@Configuration
public class AwsCredentialsConfiguration {
	@Value("${cloud.aws.credentials.profile-name}")
	private String profileName;
	private final static Logger LOGGER = LoggerFactory.getLogger(AwsCredentialsConfiguration.class);

	public AwsCredentialsConfiguration() {
		LOGGER.trace("Joining AWS Credentials Configuration");
	}

	@Bean
	@Profile("local")
	@Primary
	public AwsCredentialsProviderChain awsCredentialsProvider() {
		return AwsCredentialsProviderChain.of(ProfileCredentialsProvider.create(profileName));
	}

	@Bean
	@Profile("!local")
	@Primary
	public AwsCredentialsProviderChain instanceProfileCredentialsProvider() {
		return AwsCredentialsProviderChain.of(InstanceProfileCredentialsProvider.create());
	}
}