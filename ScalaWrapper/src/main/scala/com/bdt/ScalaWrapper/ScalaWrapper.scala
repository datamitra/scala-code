package com.bdt.ScalaWrapper

import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object ScalaWrapper {
  def main(args: Array[String]): Unit = {
    val logger = Logger.getRootLogger
    val conf=new SparkConf()
    val spark=SparkSession.builder()
    .config(conf)
    .config("hive.exec.dynamic.partition.mode","nonstrict")
    .enableHiveSupport()
    .getOrCreate()
    
    val sc=spark.sparkContext
    
    logger.info("Application ID is :"+sc.applicationId)
    logger.warn("Application ID is :"+sc.applicationId)
    
    //Read CSV file
    //stepno,temptable?, broadcast?,cache?,temptablename,sqlfile
    //1,Y,Y,Y,"xyz",step1.hql   select
    //2,N,N,N,,step2.hql  insert into
    logger.info("Reading from file :"+args(0))
    val job_steps=scala.io.Source.fromFile(args(0)).getLines().filter(!_.isEmpty())
    .map(line=>line.split(",").map(_.trim())).toArray
    
    def hql_query(loc:String)={
      scala.io.Source.fromFile(loc).mkString
    }
     
    for(i <- job_steps.indices){
      createTable(job_steps(i)(1),job_steps(i)(2),job_steps(i)(3),job_steps(i)(4),hql_query(job_steps(i)(5)))
    }
    def createTable(TempTableReq:String,BroadCast:String,Cached:String,TempTableName:String,hql_text:String){
      if(TempTableReq=="Y"){
            if(Cached=="Y"){
              logger.info("Temp Table name is:"+TempTableName)
              spark.sql(hql_text).cache().createOrReplaceTempView(TempTableName)
            }
            else{
              spark.sql(hql_text).createOrReplaceTempView(TempTableName)
            }
          //Still temp table required
        if(BroadCast=="Y"){
          sc.broadcast(TempTableName)
        }
        else{ // temp table not required
          spark.sql(hql_text)
          logger.info("*********** Job Succeeded*********")

        }
    }    

  }
    sc.stop()
    spark.stop()
} // main method end
  
}//Object end