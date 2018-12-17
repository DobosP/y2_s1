package model;

import DataStructure.IntHeap;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

public class BoolExpresion extends Expresion{
    Expresion first;
    Expresion second;
    String operation;
    public BoolExpresion(
            Expresion _first,
            Expresion _second,
            String _operation
    ){
        first = _first;
        second = _second;
        operation = _operation;
    }

    @Override
    public String  toString(){
        return first.toString() + " " + operation + " " + second.toString();
    }

    @Override
    int eval(MyIntDict<String, Integer> tab, IntHeap heap) throws MyException {

        int first_result = first.eval(tab, heap);
        int second_result = second.eval(tab, heap);
        int result = 0;

        switch (operation) {
            case "==": {
                result = (first_result == second_result) ? 1 : 0;
                break;
            }
            case "!=": {
                result = (first_result != second_result) ? 1 : 0;
                break;
            }
            case ">=": {
                result = (first_result >= second_result) ? 1 : 0;
                break;
            }
            case "<=": {
                result = (first_result <= second_result) ? 1 : 0;
                break;
            }
            case "<": {
                result = (first_result < second_result) ? 1 : 0;
                break;
            }
            case ">": {
                result = (first_result > second_result) ? 1 : 0;
                break;
            }
            default:
                break;
        }

        return  result;
    }
}
