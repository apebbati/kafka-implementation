package com.queuing.kafka.consumer;

public abstract class Consumer {
	private final String consumerName;

	Consumer(String consumerName) {
		this.consumerName = consumerName;
	}

	public abstract void notify(String message);
	
	public String getConsumerName() {
		return consumerName;
	}
}
