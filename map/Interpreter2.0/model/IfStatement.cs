using System;
using DataStructure;
namespace models
{
    public class IfStatement : IntStatement
    {
        Expression exp;
        IntStatement first;
        IntStatement second;

        public IfStatement(Expression _exp,
                        IntStatement _first,
                        IntStatement _second){

            exp = _exp;
            first = _first;
            second = _second;
        }


        override public String ToString() {
            return "IF(" + exp.ToString() + ")THEN(" +
                    first.ToString() + ")ELSE(" + second.ToString() + ")";
        }

         public ProgState execute(ProgState state) {
            MyIntDict<String, int> tabel = state.getSymTable();
            MyIntStack<IntStatement> stack = state.getExecStack();
            int val;
            try {
                val = exp.eval(tabel);
            }
            catch (MyException.MyException e){
                throw e;
            }
            if(val != 0) {
                stack.push(first);
            }
            else{
                stack.push(second);
            }
            return null;
        }
    }
}