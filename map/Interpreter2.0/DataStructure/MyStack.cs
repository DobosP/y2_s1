using System.Collections.Generic;
using System;
namespace DataStructure
{
    public class MyStack<T> : MyIntStack<T>
    {
        Stack<T> stack;

        public MyStack(){
            stack = new Stack<T>();
        }


        public void push(T val) {
            stack.Push(val);
        }

       
        public T pop() {
            return stack.Pop();
        }

        public bool empty() {
            return stack.Count == 0;
        }

       
        override public String ToString() {
            String printstr = "";
            printstr += "ExecStack:";
            List<T> array = new List<T>(stack);
            array.Reverse();
            foreach(T e in array)
                printstr += "\n" + e.ToString();
            return printstr;
        }
        
    }
}