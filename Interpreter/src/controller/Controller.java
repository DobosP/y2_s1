package controller;
import model.IntStatement;
import repository.IntRepository;
import repository.Repository;
import model.*;
import DataStructure.*;
import ExceptionHandling.MyException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;


public class Controller {
    private IntRepository rep;
    public Controller(IntRepository _rep){
        rep = _rep;
    }


    public void addPrgState(ProgState state){
        rep.addPrgState(state);
    }

    public ProgState OneStep(int index) throws MyException {
        ProgState state;
        try {
            state = rep.getProgState(index);
        }
        catch (MyException e){
            throw e;
        }

        displayProgState(state);
        System.out.println("\n");
        MyIntStack<IntStatement> stack = state.getExecStack();
        if(stack.empty()){
            throw new MyException("Stack is empty!");
        }
        IntStatement statement = stack.pop();
        ProgState newstate;
        try {
          newstate = statement.execute(state);
        }
        catch (MyException e){
            throw e;
        }
        displayProgState(newstate);

        return newstate;

    }


    public void AllSteps(int index) throws MyException {

        try {
            while (true) {
                ProgState prg = rep.getProgState(index);
                prg.getHeap().setContent(
                        conservativeGarbageCollector(
                                prg.getSymTable().getContent().values(),
                                prg.getHeap().getContent()));
                this.OneStep(index);
                rep.logPrgStateExec(index);
            }
        } catch (MyException e) {
            throw e;
        } finally {
            ProgState prg = rep.getProgState(index);

            prg.getFileTable().getContent().forEach((k, v) -> {
                try {
                    v.getsecond().close();
                } catch (IOException e) {

                }
            });

            prg.getFileTable().getContent().clear();

            displayProgState(prg);
        }
    }

    public ProgState getState(int index) throws MyException{
        try {
            return this.rep.getProgState(index);
        }
        catch (MyException e){
            throw e;
        }
    }

     Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap){
        return heap.entrySet().stream().
                filter(e->symTableValues.contains(e.getKey())).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public void displayProgState(ProgState state){
        System.out.println(state.toString());
    }

}
