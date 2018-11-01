package com.bdt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


public class KafkaJavaConsumer {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
	
	Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("group.id", "cg1");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    Consumer<String, String> consumer = new KafkaConsumer<String,String>(props);
    consumer.subscribe(Arrays.asList("test"));
    
    
    
    String myDriver = "com.mysql.jdbc.Driver";
    String myUrl = "jdbc:mysql://localhost/test_db";
    Class.forName(myDriver);
    Connection conn = DriverManager.getConnection(myUrl, "root", "root");
  
    String query = " insert into stu (id, name, dept)"
   	        + " values (?, ?, ?)";
    try{
    	while (true) {
    
        ConsumerRecords<String, String> records = consumer.poll(100);
        for(ConsumerRecord<String, String> record:records) {
        	System.out.println("Key::"+record.key()+"--value::"+record.value()+"--Topic"+record.topic()+
        			"--PARTIITON::"+record.partition()+"--OFFSET::"+record.offset());
        
        	

             
             	//stu1002,1000,dept1
            	      // create the mysql insert preparedstatement
            	      PreparedStatement preparedStmt = conn.prepareStatement(query);
            	      preparedStmt.setInt(1, Integer.parseInt(record.key()));
            	      preparedStmt.setString (2, record.value().split(",")[0]);
            	      preparedStmt.setString  (3, record.value().split(",")[2]);
            	      
            	      // execute the preparedstatement
            	      preparedStmt.execute();
            	      
        }
        
	}
    }
    finally {
    	conn.close();
	}
    
}
}
	
	
	
	
	
	