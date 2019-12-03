package com.queuing.kafka.factory;

import com.queuing.kafka.model.TopicModel;
import com.queuing.kafka.topic.Topic;
import com.queuing.kafka.topic.TopicImpl;
import com.queuing.kafka.util.StringFunctions;

public class TopicFactory {
	private static TopicFactory instance;
	private TopicModel topicModel;

	private TopicFactory() {
		topicModel = TopicModel.getInstance();
	}

	public static TopicFactory getInstance() {
		if (instance == null)
			instance = new TopicFactory();
		return instance;
	}

	public Topic createTopic(String topicName) {
		if (StringFunctions.isEmpty(topicName)) {
			System.out.println("Topic name cannot be empty.");
			return null;
		} else if (topicModel.topicExists(topicName)) {
			System.out.println("Topic '" + topicName + "' already exists.");
			return null;
		}

		Topic topic = new TopicImpl(topicName);
		topicModel.addTopic(topic);

		return topic;
	}
}
