package com.dynamodb.aws.springboot.springbootawsdynamodb.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.dynamodb.aws.springboot.springbootawsdynamodb.dto.MetricsPostRequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.CreateTableResponse;
import software.amazon.awssdk.services.dynamodb.model.DeleteTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@ContextConfiguration(initializers = MetricsApiTest.DynamoDBInitializer.class)
public class MetricsApiTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	DynamoDbClient dynamoDbAsyncClient;

	@Value("${metrics.table}")
	private String tableName;

	private static final int DYNAMODB_PORT = 8000;

	@Container
	public static GenericContainer dynamodb = new GenericContainer<>("amazon/dynamodb-local")
			.withExposedPorts(DYNAMODB_PORT);

	public static class DynamoDBInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		@Override
		public void initialize(ConfigurableApplicationContext applicationContext) {
			TestPropertyValues.of(String.format("application.dynamodb.endpoint: http://%s:%s", dynamodb.getHost(),
					dynamodb.getMappedPort(DYNAMODB_PORT))).applyTo(applicationContext);
		}
	}

	@BeforeEach
	public void beforeTest() {
		CreateTableResponse createTable = dynamoDbAsyncClient.createTable(CreateTableRequest.builder()
				.tableName(tableName)
				.attributeDefinitions(AttributeDefinition.builder().attributeName("id").attributeType("S").build())
				.keySchema(KeySchemaElement.builder().attributeName("id").keyType(KeyType.HASH).build())
				.provisionedThroughput(
						ProvisionedThroughput.builder().readCapacityUnits(5l).writeCapacityUnits(5l).build())
				.build());
	}

	@AfterEach
	public void afterTest() {
		dynamoDbAsyncClient.deleteTable(DeleteTableRequest.builder().tableName(tableName).build());
	}

	@Test
	public void safiraMetricsAPIInvoked() throws JsonProcessingException, Exception {
		MetricsPostRequestBody metricsPostRequestBody = new MetricsPostRequestBody();
		metricsPostRequestBody.setMetricsId("1");
		metricsPostRequestBody.setLicenseId("1");
		metricsPostRequestBody.setCommand("test");

		mockMvc.perform(post("/metrics/api").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(metricsPostRequestBody))).andExpect(status().isCreated());
	}

	@Test
	public void safiraMetricsURL() throws JsonProcessingException, Exception {
		MetricsPostRequestBody metricsPostRequestBody = new MetricsPostRequestBody();
		// metricsPostRequestBody.setMetricsId("1");
		// metricsPostRequestBody.setLicenseId("1");

		mockMvc.perform(post("/metrics/api").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(""))).andDo(print()).andExpect(status().isCreated());
	}

}
