package com.bdt.kafka.scala;

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime


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
  
   //{"quoteId":"281032450","custAccountId":"518464","quoteName":null,"takeOverFlag":false,"federalFlag":false,"nonStandardQuoteFlag":false,"totalQuotePriceUsd":580.05,"totalQuotePrice":580.05,"quoteListPrice":990.0,"quoteNetPrice":580.05,"lastUpdateDate":1506380822951,"creationDate":1506380485000,"orderCompletionDate":null,"createdBy":"gcastillo11","currencyCode":"USD","distiId":"2019","resellerBillToId":"403187603","invoiceToPartySiteId":null,"quoteStatusId":"10005"}
   
   streamingInputDF.printSchema()
   //println(streamingInputDF.count())
   
   import org.apache.spark.sql.functions.get_json_object
   
 /* val noOfLines= streamingInputDF
  .select(get_json_object (("value").cast("string"),"$.quoteId")
      .alias("quoteId"))
      .groupBy($"quoteId")
      .count()
 */     
   val query=streamingInputDF
   .writeStream.
   format("orc")
   .start("WHere to store)
   .outputMode("append")
   .start()
  
   query.awaitTermination()

  }
}