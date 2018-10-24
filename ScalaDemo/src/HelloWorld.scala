import java.util.StringTokenizer


//scala is pure OOP -> everythign should be object
//singleton object created using object keyword
object HelloWorld  {
  //Program execution will start from main method or if prgram is extending from APP , 
  // execution starts from first line and commmand line arguements are available in variable "args"
  def main(args: Array[String]): Unit = {
     for(a <- args) println("arguments are "+a)
     args.foreach(println)
     println("+++++++++++++++++++++")
     val stu1=new Student(1,"student1","dept1",1000.0)
     
    println("Student ID:::"+ stu1.id) // here you are calling id() method
    stu1.id=100   // here you are actuall calling id_$eq(int) method
    println("Student ID:::"+ stu1.id)
     println(stu1.dept)
   println(  stu1.getConcession())
   stu1.setConcession(20.0f)
        println(  stu1.getConcession())
     val stu2=new Student(fee=1000.0,name="stu2",id=2)
      println("Student ID:::"+ stu2.id)
       println("Student dept is :::"+ stu2.dept)
       
       val stu3=new Student(id=3,fee=999.9)
      println("Student ID:::"+ stu3.name)
       println("Student dept is :::"+ stu3.dept)
       
       val stu4=new Student()
      println("Student ID:::"+ stu4.id)
       println("Student dept is :::"+ stu4.getConcession())
       println("Student Concession is :::"+ stu4.getConcession())
println("__________________")
 println("my hibbies are "+Student.hobbies)
 Student.getConnection()
 
 var e1=Emp(1,"emp1","dept1",100.0f)
var e2=e1 //deep copy
val e3=e2.copy(id=10)
e1=Emp(200,"emp1","dept1",100.0f)

e1.productIterator.foreach(println)
println(e1)
println("Emp 2::"+e2)
 println(e3)
 
 val l1=scala.collection.mutable.ListBuffer(10,20,30)
 val l2=l1
 println(l1)
     println(l2)
     l2(0)=10000
     println("list l1"+l1)
     println(l2)

    
  }
}


class Student(var id:Int, var name:String, val dept:String="dept_X", fee:Double){
  println("+++INSIDE STU CLASS+++")
  private var concession:Float=10.0f;
  def getConcession():Float={ 
    println("Inside get Concession and my fee is :::"+fee)
    
    concession
  }
   val fn1=(x:Int)=>{
  println(x)
  x+10000
   }
  
  

  def setConcession( x:Float){
    this.concession=x;
  }
  //Auxillary Constructors
  def this( id:Int,  fee:Double){
      
    this(id,"Def student",fee=fee)
  }
  def this(){
    this(100,9999.9)
  }
}

//companion object 
object Student{
  val hobbies="reading"
  def getConnection(){
    println("++++++Inside Connection method")
  }

}


case class Emp(id:Int,name:String,dept:String,sal:Float)
//val e1=Emp(1,"emp1","dept1",100.0f)
//val e2=e1
//val e3=e2(id=10)






