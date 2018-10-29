package com.bdt.kafka.scala;

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime

//C:\Users\dgangapa\Downloads\spark-2.3.1-bin-hadoop2.7

//kafka - 2.11-2.0.0
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10
//https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10_2.11/2.3.1
//https://mvnrepository.com/artifact/org.apache.spark/spark-sql-kafka-0-10_2.11/2.3.1

object SparkStreamingDemo1 {
  def main(args: Array[String]): Unit = {
    
  val spark=SparkSession
   .builder()
   .master("local[*]")  
   .appName("SparkSessionKafkaExample")
   .getOrCreate()
   
   val streamingInputDF=spark.readStream.format("kafka")
   .option("kafka.bootstrap.servers", "localhost:9092")
   .option("subscribe","test")
   .load()
  
   streamingInputDF.printSchema()
   //println(streamingInputDF.count())
  val noOfLines= streamingInputDF.groupBy("value").count()
   val query=noOfLines
   .writeStream
   .format("console")
   .outputMode("complete")
   .trigger(ProcessingTime("5 seconds"))
   .start()
   query.awaitTermination()

  }
}