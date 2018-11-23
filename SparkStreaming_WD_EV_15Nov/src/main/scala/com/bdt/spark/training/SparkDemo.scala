package com.bdt.spark.training

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.TaskContext
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.HasOffsetRanges
import org.apache.spark.streaming.kafka010.OffsetRange
import org.apache.spark.SparkConf



object SparkDemo {
  def main(args: Array[String]): Unit = {
  /*
    val conf=new SparkConf()
   val spark=SparkSession.builder()
   .config("hive.exec.dynamic.partition.mode", "true")
   .config(conf)
   .master("local[*]")
   .getOrCreate()
   //.enableHiveSupport()
   
 // val sc=spark.sparkContext
  // sc.setLogLevel("WARN")
  //htts://spark.apache.org/docs/latest/streaming-programming-guide.htm
  //https://joncraton.org/blog/46/netcat-for-windows/
  
  
 // import spark.implicits._

  //https://databricks.com/blog/2017/04/04/real-time-end-to-end-integration-with-apache-kafka-in-apache-sparks-structured-streaming.html
  //kafka/libs folder. You should see files like kafka_2.10-0.8.2-beta.jar, where 2.10 is Scala version and 0.8.2-beta
 //scala 2.11, lkafka 2.0.0
  
  val streamingDF = spark.readStream
  .format("kafka")
  .option("kafka.bootstrap.servers", "localhost:9092")
  .option("subscribe", "test")
  .load()

  streamingDF.printSchema()
  
streamingDF.createOrReplaceTempView("kafkaData")
  println("after table creation *****************")
  val df1=spark.sql("select sum(cast(value as string) ) from kafkaData ")
  println("after select creation *****************")
  df1.writeStream.format("memory").outputMode("complete").start()
  
  */
 // -------------------------
  val kafkaParams = Map[String, Object](
  "bootstrap.servers" -> "localhost:9092",
  "key.deserializer" -> classOf[StringDeserializer],
  "value.deserializer" -> classOf[StringDeserializer],
  "group.id" -> "grp1",
  "auto.offset.reset" -> "latest",
  "enable.auto.commit" -> "false"
   )

//

// for the below, we need to add streaming-kafka jar
// for above spark.readstrea add sql kafka jar
import org.apache.spark.streaming.Duration
//import org.apache.spark.streaming.kafka010._

import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

val topics = Array("test")
 val conf=new SparkConf().setMaster("local[*]")
   .setAppName("sample app***")
   .set("spark.app.name","sample app")
   
  
val streamingContext=new StreamingContext( conf, new Duration(5000))
val stream = KafkaUtils.createDirectStream[String, String](
  streamingContext,
  PreferConsistent,
  Subscribe[String, String](topics, kafkaParams)
)
println(" after Streaming Context *******888")


/*stream.map(record => (record.key, record.value)).foreachRDD { rdd =>
  val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
  rdd.foreachPartition { iter =>
    val o: OffsetRange = offsetRanges(TaskContext.get.partitionId)
    println(s"${o.topic} ${o.partition} ${o.fromOffset} ${o.untilOffset}")
  }
  }
*/  
//https://stackoverflow.com/questions/24519660/why-does-starting-streamingcontext-fail-with-illegalargumentexception-requirem
// stream.print() instead of stream.print, use below

stream.map(record=>(record.value().toString,
    record.partition(),record.offset())).print
   
    println(" after map *******888")

  streamingContext.start()
  println(" after start Context *******888")

  streamingContext.awaitTermination()

  
  }

  
}