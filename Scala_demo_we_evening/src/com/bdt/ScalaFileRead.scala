package com.bdt

import scala.io.BufferedSource
import scala.util.control.Exception.Finally

object ScalaFileRead {
  def main(args: Array[String]): Unit = {
    // scala.collection.immutable.  -> scala
    var J=0
    val filename="C:\\Users\\dgangapa\\Desktop\\test.txt"
   /* for (i<- scala.io.Source.fromFile(filename).getLines())
    {
      println(i);
      J=J+1;
      println(J)
    }*/
    import scala.io.Source 
    println(Source.fromFile(filename).mkString.split(";")(1).trim())
    
    scala.io.Source.fromFile(filename).getLines().toList
    .filter(!_.isEmpty()).foreach(x=>println("****"+x))
    
   
      val  f=scala.io.Source.fromFile(filename)
       println(f.mkString)
      f.close()
    
   
      
    
  }
}