package com.dynamodb.aws.springboot.springbootawsdynamodb.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.dynamodb.aws.springboot.springbootawsdynamodb.model.MetricsModel;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class MetricsRepository {
	private DynamoDbTable<MetricsModel> CLIENT_TABLE;
	private final static Logger LOGGER = LoggerFactory.getLogger(MetricsRepository.class);

	public MetricsRepository() {
		LOGGER.trace("Joining Metrics Repository");
	}

	@Autowired
	public MetricsRepository(DynamoDbEnhancedClient dynamoDbEnhancedMetrics,
			@Value("${metrics.table}") String tableName) {
		this.CLIENT_TABLE = dynamoDbEnhancedMetrics.table(tableName, TableSchema.fromBean(MetricsModel.class));
	}

	public UUID saveMetrics(MetricsModel metric) {
		UUID uuid = UUID.randomUUID();
		LOGGER.info("The generated UUID is: {}", uuid);
		metric.setId(uuid.toString());
		metric.setCreateAt(LocalDateTime.now());
		CLIENT_TABLE.putItem(metric);
		return uuid;
	}

	public MetricsModel getMetrics(String id) {
		LOGGER.info("Getting metrics with id: {}", id);
		Key id1 = Key.builder().partitionValue(id).build();
		return CLIENT_TABLE.getItem(r -> r.key(id1));
	}

	public List<MetricsModel> getAllMetrics() {
		LOGGER.info("Getting all metrics");
		Iterator<MetricsModel> metrics = CLIENT_TABLE.scan().items().iterator();
		List<MetricsModel> user = new ArrayList<>();
		while (metrics.hasNext()) {
			MetricsModel metric = metrics.next();
			user.add(metric);
		}
		return user;
	}

	public MetricsModel deleteMetrics(String id) {
		LOGGER.info("Deleting metrics with id: {}", id);
		Key id1 = Key.builder().partitionValue(id).build();

		return CLIENT_TABLE.deleteItem(id1);
	}

	public void updateMetrics(MetricsModel metric) {
		LOGGER.info("Updating metrics with id: {}", metric.getId());
		CLIENT_TABLE.updateItem(metric);
		// CLIENT_TABLE.putItem(metric);
	}
}