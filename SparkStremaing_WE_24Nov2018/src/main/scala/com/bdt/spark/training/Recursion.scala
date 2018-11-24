package com.bdt.spark.training

import scala.annotation.tailrec

object Recursion {
  def main(args: Array[String]): Unit = {
  // println("without tail rec"+fact(5000))
    //Exception in thread "main" java.lang.StackOverflowError

  def fact(n:Int):Int={
      println("*** n value is "+n)
    if (n==0){
      1
    }else {
      n+fact(n-1)
    }
  }
    
    println("**** fact1::::"+fact1(3))
    def fact1(n:Int):Int= n match {
      case 0 => 1
      case _ => n*fact1(n-1)
    }
    
   println("Result is"+ fact_tailrec(5000,1))
    @tailrec
    def fact_tailrec(n:Int,res:Long):Long={
     println(n+"::::"+res)
      if(n==0) res
      else fact_tailrec(n-1, res+n)
    }
      
      
   
  }
   
}