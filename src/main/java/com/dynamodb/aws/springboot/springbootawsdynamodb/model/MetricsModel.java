
package com.dynamodb.aws.springboot.springbootawsdynamodb.model;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.MetricsPostRequestBody;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class MetricsModel {

	private String id;
	private String command;
	private String licenseId;
	private LocalDateTime createAt;
	private final static Logger LOGGER = LoggerFactory.getLogger(MetricsModel.class);

	@DynamoDbPartitionKey
	public String getId() {
		return id;
	}

	public MetricsModel() {
		LOGGER.trace("Joining Metrics model");
	}

	public MetricsModel(MetricsPostRequestBody metricsDTO) {
		this.id = metricsDTO.getMetricsId();
		this.licenseId = metricsDTO.getLicenseId();
		this.command = metricsDTO.getCommand();
	}

	public void setId(String id) {
		this.id = id;
	}

	@DynamoDbAttribute("command")
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@DynamoDbAttribute("licenseId")
	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

}
