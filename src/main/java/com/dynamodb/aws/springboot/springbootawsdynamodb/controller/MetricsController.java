package com.dynamodb.aws.springboot.springbootawsdynamodb.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dynamodb.aws.springboot.springbootawsdynamodb.api.MetricsApi;
import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.InlineResponse201;
import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.MetricsPostRequestBody;
import com.dynamodb.aws.springboot.springbootawsdynamodb.model.MetricsModel;

@RestController
public class MetricsController implements MetricsApi {

	@Autowired
	private com.dynamodb.aws.springboot.springbootawsdynamodb.repository.MetricsRepository clientRepository;
	private final static Logger LOGGER = LoggerFactory.getLogger(MetricsController.class);

	public MetricsController() {
		LOGGER.trace("Joining Metrics Controller");
	}

	@ResponseBody
	public ResponseEntity<InlineResponse201> metricsApiPost(
			@Valid @NotNull MetricsPostRequestBody metricsPostRequestBody) {

		LOGGER.trace("Joining metricsApiPost {}", metricsPostRequestBody);

		MetricsModel metrics = new MetricsModel();
		metrics.setId(UUID.randomUUID().toString());
		metrics.setCommand(metricsPostRequestBody.getCommand());
		metrics.setLicenseId(metricsPostRequestBody.getLicenseId());
		UUID save = clientRepository.saveMetrics(metrics);
		InlineResponse201 inline = new InlineResponse201();
		inline.setRequestId(save.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(inline);
	}

}