package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;



public class ArithExpresion extends Expresion {
    Expresion first;
    Expresion second;
    int op;

    public ArithExpresion(int _op,
                          Expresion _first,
                          Expresion _second){
        first = _first;
        second = _second;
        op = _op;

    }

    @Override
    int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException{
        int val = 0;

        switch (op){
            case 1:{
                val = first.eval(tab, heap) + second.eval(tab, heap);
                break;
            }
            case 2:{
                val = first.eval(tab, heap) - second.eval(tab, heap);
                break;
            }
            case 3:{
                val = first.eval(tab, heap) * second.eval(tab, heap);
                break;
            }
            case 4:{
                int divisor = second.eval(tab, heap);
                if(divisor == 0){
                    throw new MyException("Divide by zero");
                }
                val = first.eval(tab, heap) / divisor;
                break;
            }
            default:
                break;

        }
        return val;
    }

    @Override
    public String toString() {
        String operator = "";
        switch (op) {
            case 1: {
                operator = "+";
                break;
            }
            case 2: {
                operator = "-";
                break;
            }
            case 3: {
                operator = "*";
                break;
            }
            case 4: {
                operator = "/";
                break;
            }
            default:
                break;
        }
        return  first.toString() + operator + second.toString();
    }
}
