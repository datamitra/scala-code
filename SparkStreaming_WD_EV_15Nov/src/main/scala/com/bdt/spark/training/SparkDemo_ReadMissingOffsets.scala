package com.bdt.spark.training

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.TaskContext
import org.apache.spark.streaming.Seconds

import org.apache.spark.SparkContext



import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.HasOffsetRanges
import org.apache.spark.streaming.kafka010.OffsetRange
  import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
  import org.apache.spark.streaming.kafka010.LocationStrategy
import org.apache.spark.streaming.Duration

import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent 
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe


//(f,0,27) read from partition 0 and offset 27
object SparkDemo_ReadMissingOffsets {
  def main(args: Array[String]): Unit = {
 
  
//

// for the below, we need to add streaming-kafka jar
// for above spark.readstrea add sql kafka jar

val topics = Array("test")
 val conf=new SparkConf().setMaster("local[*]")
   .setAppName("sample app***")
   .set("spark.app.name","sample app")
   
  
val streamingContext=new StreamingContext( conf, new Duration(5000))
 
  
   val offsetRanges = Array(
      OffsetRange("test", 0, 50,53)
      ,OffsetRange("test", 0, 61,70)
    )
  
    //http://blog.cloudera.com/blog/2017/06/offset-management-for-apache-kafka-with-apache-spark-streaming/
        val sc = new SparkContext(new SparkConf)
 import org.apache.spark.streaming.kafka010.LocationStrategy
  //  KafkaUtils.createRDD(sc, kafkaParams, offsetRanges, PreferConsistent)
   // val rdd = KafkaUtils.createRDD[String, String](sc, kafkaParams, offsetRanges,PreferConsistent)
 
 val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "localhost:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "grp1",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> (false: java.lang.Boolean)
   )

 
   // val rdd = KafkaUtils.createRDD[String, String](sc, kafkaParams, offsetRanges, PreferConsistent)

//    [String, String, StringDeserializer, StringDeserializr] 
//  (sc, kafkaParams, offsetRanges, org.apache.spark.streaming.kafka010.LocationStrategy.PreferConsistent)
//    
    println(" after Streaming Context *******888")

println(" after map *******888")

  //streamingContext.start()
  //println(" after start Context *******888")

  //streamingContext.awaitTermination()

  
  }

  
}