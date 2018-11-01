package com.bdt

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.log4j.Logger
import org.apache.log4j.Level

object SparkDemo {
  
  //For developing spark applicaitons create scala project
  //Add spark dependent jar -> prpject->buildpath->configure bildpath-> add external jars
  //Add all jars in Spark/jars folder
  //OR - Use SBT/MVN
  
  // For Spark applications::
  //Testing - we create sparkSession with sparkconfiguration hard coded
  //Running in production -> we pass emmpty spark configuraiton and provide all configuraion
  //in Command line as arguments  using  --conf or --properties file
  
  def main(args: Array[String]): Unit = {
    //create configuration and create sparksession object
        Logger.getLogger("org").setLevel(Level.DEBUG)

    val conf=new SparkConf()
    
    /* Production - create empty configuration
     * val conf=new SparkConf()
     */
    
    /*
 val warehouseLocation = "file:${system:user.dir}/spark-warehouse"
 val spark = SparkSession
   .builder()
   .appName("SparkSessionZipsExample")
   .config("spark.sql.warehouse.dir", warehouseLocation)
   .enableHiveSupport()   // Required for Working with HQL
   .getOrCreate()
   * 
   */
    

    val spark=SparkSession
    .builder()
    .appName("SparkDemo1")
    .config(conf)
    .enableHiveSupport()
    .getOrCreate()
    
    
    val sc=spark.sparkContext
    
    import spark.implicits._
    
    //Cleansing / RDDs
    val empRDD=sc.textFile(args(0));
    empRDD.foreach(println)
  empRDD.filter(line => ! line.contains("id,name,sal"))
  .map(line=>(line.split(",")(0),line.split(",")(1),line.split(",")(2),line.split(",")(3)))
  .foreach(println)
  
  val empDF=empRDD.filter(line => ! line.contains("id,name,sal"))
  .map(line=>(line.split(",")(0),line.split(",")(1),line.split(",")(2),line.split(",")(3)))
  .toDF("id","name","sal","dept")
  empDF.printSchema()
  empDF.show()
  
  empDF.createOrReplaceTempView("emp_table")
  val empDF1=spark.sql("select id,name,sal+1000 as sal1,dept from emp_table")
  empDF1.show()
  //empDF1.write.saveAsTable("emp_table1")
  spark.sql("create table if not exists emp_demo as select * from emp_table")
  spark.sql("insert overwrite table emp_demo select * from emp_table")
  println("*******************")
  }
  
  
}