package com.queuing.kafka.topic;

import java.util.HashMap;
import java.util.Map;

import com.queuing.kafka.consumer.Consumer;

public abstract class Topic {
	private Map<String, Consumer> subscribedConsumers;
	private final String topicName;

	Topic(String topicName) {
		this.topicName = topicName;
		this.subscribedConsumers = new HashMap<String, Consumer>();
	}

	public boolean subscribeConsumer(Consumer consumer) {
		if (this.subscribedConsumers.containsKey(consumer.getConsumerName()))
			return false;
		else {
			this.subscribedConsumers.put(consumer.getConsumerName(), consumer);
			return true;
		}
	}

	public boolean unsubscribeConsumer(Consumer consumer) {
		if (!this.subscribedConsumers.containsKey(consumer.getConsumerName()))
			return false;
		else {
			this.subscribedConsumers.remove(consumer.getConsumerName());
			return true;
		}
	}

	public void notifyConsumers(String message) {
		Thread thread;
		for (Consumer consumer : subscribedConsumers.values()) {
			thread = new Thread() {
				public void run() {
					consumer.notify(message);
				}
			};
			thread.start();
		}
	}

	public abstract void pushMessage(String message);

	public String getTopicName() {
		return topicName;
	}
}
