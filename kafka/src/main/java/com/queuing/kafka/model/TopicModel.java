package com.queuing.kafka.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.queuing.kafka.topic.Topic;

public class TopicModel {
	private static TopicModel instance;
	private Map<String, Topic> topics;

	private TopicModel() {
		this.topics = new HashMap<String, Topic>();
	}

	public static TopicModel getInstance() {
		if (instance == null)
			instance = new TopicModel();
		return instance;
	}

	public boolean topicExists(String topicName) {
		return topics.containsKey(topicName);
	}

	public void addTopic(Topic topic) {
		topics.put(topic.getTopicName(), topic);
	}

	public Topic getTopic(String topicName) {
		return topics.get(topicName);
	}

	public List<Topic> getTopicsList() {
		return topics.values().stream().collect(Collectors.toList());
	}

}
