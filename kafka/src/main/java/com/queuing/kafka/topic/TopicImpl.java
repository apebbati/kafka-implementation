package com.queuing.kafka.topic;

public class TopicImpl extends Topic {

	public TopicImpl(String topicName) {
		super(topicName);
	}

	@Override
	public void pushMessage(String message) {
		message = "Message pushed to " + this.getTopicName() + ": " + message;
		System.out.println(message);
		this.notifyConsumers(message);
	}

}
