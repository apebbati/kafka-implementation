package com.queuing.kafka.factory;

import com.queuing.kafka.consumer.Consumer;
import com.queuing.kafka.consumer.ConsumerImpl;
import com.queuing.kafka.model.ConsumerModel;
import com.queuing.kafka.util.StringFunctions;

public class ConsumerFactory {
	private static ConsumerFactory instance;
	private ConsumerModel consumerModel;

	private ConsumerFactory() {
		consumerModel = ConsumerModel.getInstance();
	}

	public static ConsumerFactory getInstance() {
		if (instance == null)
			instance = new ConsumerFactory();
		return instance;
	}

	public Consumer createConsumer(String consumerName) {
		if (StringFunctions.isEmpty(consumerName)) {
			System.out.println("Consumer name cannot be empty.");
			return null;
		} else if (consumerModel.consumerExists(consumerName)) {
			System.out.println("Consumer '" + consumerName + "' already exists.");
			return null;
		}

		Consumer consumer = new ConsumerImpl(consumerName);
		consumerModel.addConsumer(consumer);

		return consumer;
	}
}
