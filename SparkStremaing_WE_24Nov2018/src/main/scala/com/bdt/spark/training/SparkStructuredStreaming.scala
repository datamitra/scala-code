package com.bdt.spark.training

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SparkStructuredStreaming {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setMaster("local[*]").setAppName("StructuredStreaming")
    val spark=SparkSession.builder().config(conf).getOrCreate()
    
    val streamDF=spark.readStream.format("kafka")
   .option("kafka.bootstrap.servers", "localhost:9092")
  .option("subscribe", "test")
  .load()
  
  streamDF.printSchema()
  println("*****")
  streamDF.writeStream
    .format("memory")
    .queryName("test123")
    .outputMode("append")
    .start()
    spark.sql("select * from test123").show()
    
  
  }
}