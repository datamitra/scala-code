package com.bdt.spark.training

object LetterCombination {
  def main(args: Array[String]): Unit = {
    val str="abc"
    
    def swap(a:String,i:Int,j:Int):String={
       val temp:Char=a(i)
       var ch_arr=a.toArray
       ch_arr(i)=ch_arr(j)
       ch_arr(j)=temp
       ch_arr.toString()
    }
   def permute( str:String, l:Int, r:Int)   //start index 0 and end index n-1
    { 
      /*  if (l == r) 
            println(str); 
        else
        { 
            val i = l
            while(i <= r) 
            { 
                str = swap(str,l,i); 
                permute(str, l+1, r); 
                str = swap(str,l,i); 
                 i++
            } 
        } */
    } 
       
    
    
    }
    
  }
