package com.queuing.kafka.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.queuing.kafka.consumer.Consumer;
import com.queuing.kafka.factory.ConsumerFactory;
import com.queuing.kafka.factory.TopicFactory;
import com.queuing.kafka.model.ConsumerModel;
import com.queuing.kafka.model.TopicModel;
import com.queuing.kafka.topic.Topic;

@Service
public class KafkaServiceImpl implements KafkaService {

	ConsumerFactory consumerFactory;
	TopicFactory topicFactory;

	ConsumerModel consumerModel;
	TopicModel topicModel;

	KafkaServiceImpl() {
		consumerFactory = ConsumerFactory.getInstance();
		topicFactory = TopicFactory.getInstance();

		consumerModel = ConsumerModel.getInstance();
		topicModel = TopicModel.getInstance();
	}

	@Override
	public Topic addTopic(String topicName) {
		return topicFactory.createTopic(topicName);
	}

	@Override
	public Consumer addConsumer(String consumerName) {
		return consumerFactory.createConsumer(consumerName);
	}

	@Override
	public void subscribeConsumer(String topicName, String consumerName) {
		Topic topic = topicModel.getTopic(topicName);
		Consumer consumer = consumerModel.getConsumer(consumerName);
		topic.subscribeConsumer(consumer);
	}

	@Override
	public void pushMessage(String topicName, String message) {
		Topic topic = topicModel.getTopic(topicName);
		topic.pushMessage(message);
	}

	@Override
	public void pushRandomMessage(int messageCount) {
		List<Topic> topics = topicModel.getTopicsList();
		Random random = new Random();
		Thread thread;
		for (int i = 1; i <= messageCount; i++) {
			int j = i;
			thread = new Thread() {
				public void run() {
					topics.get(Math.abs(random.nextInt() % topics.size())).pushMessage("Message " + j);
				}
			};
			thread.start();

		}
	}

}
