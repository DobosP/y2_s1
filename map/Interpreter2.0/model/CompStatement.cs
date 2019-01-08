using System;
using DataStructure;
namespace models
{
    public class CompStatement : IntStatement
    {
        IntStatement first;
        IntStatement second;

        public CompStatement(IntStatement _first, IntStatement _second){
            first = _first;
            second = _second;
        }

  
        override public String ToString() {
            return "(" + first.ToString() + ";" + second.ToString() + ")";
        }

        public ProgState execute(ProgState state) {
            MyIntStack<IntStatement> stack = state.getExecStack();
            stack.push(second);
            stack.push(first);
            return null;
        }
        
    }
}