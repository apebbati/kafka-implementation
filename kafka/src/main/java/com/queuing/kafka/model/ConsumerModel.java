package com.queuing.kafka.model;

import java.util.HashMap;
import java.util.Map;

import com.queuing.kafka.consumer.Consumer;

public class ConsumerModel {
	private static ConsumerModel instance;
	private Map<String, Consumer> consumers;

	private ConsumerModel() {
		this.consumers = new HashMap<String, Consumer>();
	}

	public static ConsumerModel getInstance() {
		if (instance == null)
			instance = new ConsumerModel();
		return instance;
	}

	public boolean consumerExists(String consumerName) {
		return consumers.containsKey(consumerName);
	}

	public void addConsumer(Consumer consumer) {
		consumers.put(consumer.getConsumerName(), consumer);
	}

	public Consumer getConsumer(String consumerName) {
		return consumers.get(consumerName);
	}

}
