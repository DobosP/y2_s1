package model;

import DataStructure.IntProcTable;
import DataStructure.*;
import ExceptionHandling.MyException;

import java.util.ArrayList;
import java.util.List;

public class call implements IntStatement {
    String procname;
    List<Expresion> args;


    public call(String name, List<Expresion> _args){
        procname = name;
        args = _args;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {

        IntProcTable proctable = state.getProgTable();

        if(!proctable.exists(procname)){
            throw new MyException("Procedure do not exits!");
        }

        Pair<List<String>,IntStatement> procdata = proctable.getValue(procname);

        List<Integer> vals = new ArrayList<>();
        for(Expresion item: args){
            vals.add(item.eval(state.getSymTable().top(),state.getHeap()));
        }
        MyIntDict<String, Integer> newsym = new MyDict<>();
        for(int i = 0; i < vals.size(); i++){
            newsym.add(procdata.getFirst().get(i),vals.get(i));
        }
        state.getSymTable().push(newsym);
        state.execStack.push(new returnstate());
        state.execStack.push(procdata.getsecond());
        return null;
    }

    @Override
    public String toString(){
        return "call " + procname + args.toString();
    }
}
