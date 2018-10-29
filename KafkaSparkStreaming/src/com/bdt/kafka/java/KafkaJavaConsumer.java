package com.bdt.kafka.java;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaJavaConsumer {
@SuppressWarnings("deprecation")
public static void main(String[] args) {
	

	Properties props = new Properties();
	//props.put("client.id", InetAddress.getLocalHost().getHostName());
	props.put("group.id", "cg1");
	//props.put("bootstrap.servers", "host1:9092,host2:9092");
	props.put("bootstrap.servers", "localhost:9092");
	
	props.put("auto.commit.interval.ms", "1000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	
	Consumer<String, String> consumer=new KafkaConsumer<String,String>(props);
    List<String> topicList=Arrays.asList("test2");
    consumer.subscribe(topicList);
    while (true) {
    	ConsumerRecords<String, String> records=consumer.poll(200 );
    	for(ConsumerRecord<String, String> record:records) {
    		System.out.println("Message KEY::"+record.key()+
    				"::OFFSET->"+record.offset()+"::Partition->"+record.partition()+
    				"::Vallue->"+record.value());
    	}
    }
	
}
}
