using System;
using DataStructure;
namespace models
{
    public class VarExpresion : Expression
    {
        String id;

        public VarExpresion(String _id){
            id = _id;
        }

        public override int eval(MyIntDict<String, int> tab) {
            try {
                return tab.get(id);
            }
            catch (MyException.MyException ex){
                throw ex;
            }
        }
 
        override public String ToString() {
            return id;
        } 
    }
}