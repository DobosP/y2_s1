using System;
using DataStructure;
namespace models
{
    public class AssignStatement : IntStatement
    {
        String id;
        Expression exp;

        public AssignStatement(String _id, Expression _exp){
            id = _id;
            exp = _exp;
        }

    
        override public String ToString() {
            return id + "=" + exp.ToString();
        }

      
        public ProgState execute(ProgState state){
            MyIntDict<String, int> tabel = state.getSymTable();
            int val;

            try {

                val = exp.eval(tabel);
                if (tabel.exists(id)) {
                    tabel.update(id, val);
                } else {
                    tabel.add(id, val);
                }
            }
            catch (MyException.MyException e){
                throw e;
            }
            return null;
        }
        
    }
}