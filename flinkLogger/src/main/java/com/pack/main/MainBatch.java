package com.pack.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class MainBatch {

	public static void main(String[] args) {
		
		String bootstrap = "localhost:9092";
		String groupId = "mx-logger-messages-data";
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", bootstrap);
		properties.setProperty("group.id", groupId);
		
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		FlinkKafkaConsumer010<String> consumer = new FlinkKafkaConsumer010<>(topiclist(), new SimpleStringSchema(), properties);
		consumer.setStartFromLatest();
		
		DataStream<String> stream = env.addSource(consumer);
		stream.map(x->1).timeWindowAll(Time.seconds(1)).reduce((a,b)->(a+b)).print();
		
		try {
			env.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> topiclist() {
		String topics = "wmx-mg-pna-offer-events,"
				+ "wmx-mg-pna-offer-price-events,"
				+ "wmx-mg-pna-seller-events,"
				+ "wmx-mg-pna-offer-inventory-events,"
				+ "wmx-mg-pna-offer-availability-events,"
				+ "wmx-mg-pna-offer-calculation-events,"
				+ "wmx-mg-pna-offer-logging";
		
		List<String> list = new ArrayList<String>();
		for(String topic: topics.split(",")) {
			list.add(topic);
		}
		return list;
	}
	
}