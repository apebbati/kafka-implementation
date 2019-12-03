package com.queuing.kafka.service;

import com.queuing.kafka.consumer.Consumer;
import com.queuing.kafka.topic.Topic;

public interface KafkaService {

	public Topic addTopic(String topicName);

	public Consumer addConsumer(String topicName);

	public void subscribeConsumer(String topicName, String consumerName);
	
	public void pushMessage(String topicName, String message);

	public void pushRandomMessage(int messageCount);

}
