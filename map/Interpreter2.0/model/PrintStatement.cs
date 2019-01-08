using System;
using DataStructure;
namespace models
{
    public class PrintStatement : IntStatement
    {
        Expression exp;

        public PrintStatement(Expression _exp){
            this.exp = _exp;
        }


        override public String ToString() {
            return "print(" + exp.ToString() + ")";
        }

        public ProgState execute(ProgState state){
            MyIntList<int> dataout = state.getOut();
            try {
                dataout.add(exp.eval(state.getSymTable()));
            }
            catch(MyException.MyException e) {
                throw e;
            }
            return null;
        }
        
    }
}