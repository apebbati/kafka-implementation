package com.queuing.kafka.consumer;

public class ConsumerImpl extends Consumer {

	public ConsumerImpl(String consumerName) {
		super(consumerName);
	}

	@Override
	public void notify(String message) {
		System.out.println("Message '" + message + "' consumed by '" + this.getConsumerName() + "'.");
	}

}
