package com.bdt;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaJavaProducer {
	public static void main(String[] args) {
		//create properties object
		Properties props = new Properties();
		 props.put("bootstrap.servers", "localhost:9092");
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		 Producer< String, String> producer=new KafkaProducer<String, String>(props);
		 //ProducerRecord
		 for (int i = 3000	; i < 3010; i++) {
			
		String key=i+"";
		String value="stu"+i+",1000"+",dept1";
		System.out.println("Studet Record is::"+value);
		 ProducerRecord record=new ProducerRecord<String, String>("test", key, value);
		 producer.send(record);
		 }
		 producer.close();
	}

}
