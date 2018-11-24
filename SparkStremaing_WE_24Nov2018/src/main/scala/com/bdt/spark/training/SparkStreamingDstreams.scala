package com.bdt.spark.training

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.OffsetRange

import org.apache.spark.SparkContext

object SparkStreamingDstreams {
  def main(args: Array[String]): Unit = {
    
    val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "localhost:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "grp1",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> "false"
   )
   
   val conf=new SparkConf().setMaster("local[*]").setAppName("streaming")
   //while running in spark-submit, make the configuration empty.
   val topics=Array("test")
   
   val offsetRanges = Array(
  // topic, partition, inclusive starting offset, exclusive ending offset
  OffsetRange("test", 0, 100, 110) )
   
   //val streamingContext=new StreamingContext(conf,new Duration(5000))
    val sparkContext=new SparkContext(conf)
    
    
    
    val kafkaParams1 = new java.util.HashMap[String, Object]
    kafkaParams1.put("bootstrap.servers" , "localhost:9092")
  kafkaParams1.put("key.deserializer" , classOf[StringDeserializer])
  kafkaParams1.put("value.deserializer" , classOf[StringDeserializer])
 kafkaParams1.put( "group.id" , "grp1")
 kafkaParams1.put( "auto.offset.reset" , "latest")
 kafkaParams1.put( "enable.auto.commit" , "false")
   
    
    
    val rdd1=KafkaUtils.createRDD[String, String](sparkContext, kafkaParams1, offsetRanges, PreferConsistent)
    rdd1.map(line =>(line.value(),line.partition(),line.offset(),line.topic))
    .foreach(println)
    
   /* val dstreams=KafkaUtils.createDirectStream[String, String](
  streamingContext,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)
)

// k,v,partition,offset,time, time of change
   dstreams.map(line =>(line.value(),line.partition(),line.offset(),line.topic)).print()
  // dstreams.map(line =>(line.value(),line.partition(),line.offset(),line.topic))
   //.saveAsTextFiles("PP", "SS")
   streamingContext.start();
    streamingContext.awaitTermination()
    
*/


  }
}