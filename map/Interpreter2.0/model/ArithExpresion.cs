using DataStructure;
using MyException;
using System;
namespace models
{
    public class ArithExpresion : Expression
    {
        Expression first;
        Expression second;
        int op;

        public ArithExpresion(int _op,
                            Expression _first,
                            Expression _second){
            first = _first;
            second = _second;
            op = _op;

        }

        override public int eval(MyIntDict<string, int> tab){
            int val = 0;

            switch (op){
                case 1:{
                    val = first.eval(tab) + second.eval(tab);
                    break;
                }
                case 2:{
                    val = first.eval(tab) - second.eval(tab);
                    break;
                }
                case 3:{
                    val = first.eval(tab) * second.eval(tab);
                    break;
                }
                case 4:{
                    int divisor = second.eval(tab);
                    if(divisor == 0){
                        throw new MyException.MyException("Divide by zero");
                    }
                    val = first.eval(tab) / divisor;
                    break;
                }
                default:
                    break;

            }
            return val;
        }

       
        override public String ToString() {
            String oper = "";
            switch (op) {
                case 1: {
                    oper = "+";
                    break;
                }
                case 2: {
                    oper = "-";
                    break;
                }
                case 3: {
                    oper = "*";
                    break;
                }
                case 4: {
                    oper = "/";
                    break;
                }
                default:
                    break;
            }
            return  first.ToString() + oper + second.ToString();
        }
    }
}