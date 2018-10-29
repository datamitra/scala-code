package com.bdt.kafka.java;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
//import org.apache.kafka.common.serialization.
// DOCUMENTATION LINK https://kafka.apache.org/documentation/
//https://docs.confluent.io/current/clients/producer.html
import org.apache.kafka.clients.producer.Callback;
public class KafkaJavaProducer {
	public static void main(String[] args) {
		Properties props = new Properties();

		// import org.apache.kafka.clients.producer.ProducerConfig;
		//one way to add is use producerconfig class
		//props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		//https://kafka.apache.org/0100/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html
		 props.put("bootstrap.servers", "localhost:9092");
		 //props.put("acks", "all"); 	 //props.put("retries", 0);  //props.put("batch.size", 16384);
		 //props.put("linger.ms", 1); 	 //props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 String topicName="test";
		 
		 //ASYNC SEND
		Producer<String	, String> producer= new KafkaProducer<>(props);
		for (int i = 3000; i < 3010; i++) {
			String key=i+"";
			String value="stu_"+i+"--id is_"+i;
			System.out.println("message value is --> "+value);
			ProducerRecord<String, String> record=new ProducerRecord<String, String>(topicName, key, value);
			producer.send(record);
		}
		// SEND METHOD RETURNS Future OBJECT
		/*
		 * The send is asynchronous and this method will return immediately 
		 * once the record has been stored in the buffer of records waiting to be sent. 
		 * This allows sending many records in parallel without blocking to wait for 
		 * the response after each one.
		 */
		/*
		 * Since the send call is asynchronous it returns a 
		 * Future for the RecordMetadata that will be assigned to this record. 
		 * TO MAKE IT SYNCHRONOUS
		 * To make writes synchronous, just wait on the returned future. 
		 * This would typically be a bad idea since it would kill throughput, 
		 * but may be justified in some cases.
		 * 
		 * Invoking get() on this future will BLOCK until the associated request completes 
		 * and then return the metadata for the record or throw any exception 
		 * that occurred while sending the record.
		 * If you want to simulate a simple blocking call you can call the get() method immediately:
		 *  producer.send(record).get();
		 */
		
		///////// do something when write is completed, Kafka returns a CallBack Object
		//Send Records Synchronously
/***************************************************************************************************/
/***************
		///import org.apache.kafka.clients.producer.Callback;
		class MyCallback implements Callback {
			public void onCompletion(RecordMetadata metadata, Exception e) {
				  System.out.println("INSIDE CALL BACK THE DATA WRITTEN IS"+"::OFFSET->"+metadata.offset()+"::::::"+metadata.partition()+":::::"+metadata.topic());
				    if (e != null)
				       e.printStackTrace();
				  }	
		}
		for (int i = 9900; i < 9910; i++) {
			String key=i+"";
			String value="WITH CALL BACK stu_"+i+"--id is_"+i;
			System.out.println("message value is --> "+value);
			ProducerRecord<String, String> record=new ProducerRecord<String, String>(topicName, key, value);
			producer.send(record, new MyCallback() );
		}
		
	
***************/		
		producer.close();

	/*
	 * Step1 - properties object and set configuration - use direct properties or ProducerConfig
	 * step2 - Create a KafkaProducer object with the properties
	 * step3 - Create a producerRecord object with the topic , key and value
	 * step4- using kafkaproducer object send method , send the producerrecord object.
	 */
		
	}
}





