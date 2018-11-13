package com.bdt

/*object ScalaDemo extends App{
   args.foreach(x=>println("argument is :::"+x))
 
}*/

object ScalaDemo {
  def main(args: Array[String]): Unit = {
    args.foreach(x=>println("Inside main - argument is :::"+x))
    
    val stu1=new Student("stu1",1000,"dept1")
    println("student name:::"+stu1.name) //here we are calling a getter method name()
    println("student fee:::"+stu1.fee)
    stu1.printDept()
    println("student gender::"+stu1.getGender())
    println("************After modifying*******")
    stu1.name="AAAAAA"    //name_$eq
    println("student name:::"+stu1.name)
    //stu1.fee=100 cannot do this
    stu1.setGender("female")
    println("student gender::"+stu1.getGender())
    
        println("*******************")
        val stu2=new Student("stu2",dept="dept2")
        println(stu2.fee)
        val stu3=new Student(dept="dept2",name="stu3",fee=8888)
             println(stu3.fee)

          val stu4=new Student("stu4")
        println(stu4.fee)
        println(stu4.printDept()) 
    
  }
   
}

// class and primary constructor will be defined in one line
class Student(var name:String,val fee:Int=999,dept:String ){
  // in java we define all member variables as private
  //then we create get and set methods to access and modify the variables.
  // primary constructor and overloaded constructors
  println("Inside Student Class")
  def printDept()={
  println("Department value is ::"+dept)
  }
  private var gender="male"
  
  def getGender():String={
    println("Inside get Gender")
    gender
  }
  def setGender(x:String):Unit={
    println("Inside Set Gender")
    gender=x
  }
  
  //Overloaded constructor or Auxiliary Constructor
  def this(name:String)={
    this(name,33333,"DefDept")
    println("Inside 2 args consturctor")
  }
  
}


