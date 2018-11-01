package com.bdt.kafka.scala;

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime

import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

//******************* NOT WORKING ********************//
//C:\Users\dgangapa\Downloads\spark-2.3.1-bin-hadoop2.7

//kafka - 2.11-2.0.0
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10_2.11/2.3.1
//https://mvnrepository.com/artifact/org.apache.spark/spark-sql-kafka-0-10_2.11/2.3.1

//C:\Users\dgangapa\Downloads\spark-2.3.1-bin-hadoop2.7

//kafka - 2.11-2.0.0
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10_2.11/2.3.1
//https://mvnrepository.com/artifact/org.apache.spark/spark-sql-kafka-0-10_2.11/2.3.1

object SparkStreamingDemoRDD {
  def main(args: Array[String]): Unit = {
    
  val spark=SparkSession
   .builder()
   .master("local[*]")  
   .appName("SparkSessionKafkaExample")
   .getOrCreate()
   
   val sc=spark.sparkContext
   
   //https://spark.apache.org/docs/2.2.0/api/scala/index.html#org.apache.spark.streaming.StreamingContext
   val ssc = new StreamingContext(sc, Seconds(5))
  //      ssc.checkpoint("checkpoint")

  val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "localhost:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "cg1",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> (false: java.lang.Boolean)
)
val topics = Array("test")
val stream = KafkaUtils.createDirectStream[String, String](
  ssc,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)

)

println("++++++++++++++")



      

      ssc.start()
      ssc.awaitTermination()

//stream.map(record => (record.key, record.value))

//stream.start()
   //https://stackoverflow.com/questions/43841091/spark2-1-0-incompatible-jackson-versions-2-7-6
      ///removed jackson core from libraries
   
  }
}