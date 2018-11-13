package com.bdt

/*object ScalaDemo extends App{
   args.foreach(x=>println("argument is :::"+x))
 
}*/

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    args.foreach(x=>println("Inside main - argument is :::"+x))
    
  }
   
}

// class and primary constructor will be defined in one line
class Student(var name:String,val fee:Int,dept:String ){
  // in java we define all member variables as private
  //then we create get and set methods to access and modify the variables.
  // primary constructor and overloaded constructors
  println("Inside Student Class")
  println("Department value is ::"+dept)
  
  private var gender="male"
  
  def getGender():String={
    println("Inside Get Gender")
    gender
  }
  def setGender(x:String){
    gender=x
  }
  
  
}


