using DataStructure;
using System;
namespace models
{
    public class ConstExpresion : Expression
    {
        int val;

        public ConstExpresion(int value){
            val = value;
        }


        public override int eval(MyIntDict<String, int> tab) {
            return val;
        }

        override public String ToString() {
            return this.val.ToString();
        }
        
    }
}