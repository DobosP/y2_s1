using System;

namespace MyException 
{
    public class MyException : Exception
    {
            public MyException(String ex) : base(ex){
       
    }

    override public String ToString() {
        return base.ToString();
    }
    public String getMessage() {
        return base.ToString();
    }
        
    }
}