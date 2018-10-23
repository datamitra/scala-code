
object HelloWorld  {
  //Program execution will start from main method or if prgram is extending from APP , 
  // execution starts from first line and commmand line arguements are available in variable "args"
  def main(args: Array[String]): Unit = {
     for(a <- args) println("arguments are "+a)
     args.foreach(println)
     println("+++++++++++++++++++++")
     val stu1=new Student(1,"student1","dept1",1000.0)
     println(stu1.dept)
    println( stu1.id)
     
     
  }
}


class Student(var id:Int, var name:String, val dept:String, fee:Double){
  private var concession:Float=10.0f;
  /*def getConcession():Float={ 
    println("Inside get Concession and my fee is :::"+fee)
    concession
  }
  def setConcession( x:Float){
    this.concession=x;
  }*/
  
}

case class Emp(id:Int,name:String,dept:String,sal:Float)