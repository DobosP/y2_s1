package model;

import DataStructure.*;
import ExceptionHandling.MyException;

public class ProgState {
    MyIntStack<IntStatement> execStack;
    MyIntStack< MyIntDict<String, Integer>> symTablestack;
    MyIntList<Integer> out;
    IntStatement originalProgram;
    IntFileTable filetable;
    IntHeap heap;
    Integer id;
    static Integer id_count = 1;
    public ProgState(MyIntStack<IntStatement> stack,
                     MyIntStack< MyIntDict<String, Integer>> symstack,
              MyIntList<Integer> list,
              IntFileTable _fileTable,
              IntHeap _heap,
              IntStatement statement){
        execStack = stack;
        symTablestack = symstack;
        out = list;
        filetable = _fileTable;
        heap = _heap;
        id = id_count;
        originalProgram = statement;
        execStack.push(statement);
        id_count += 1;
    }

    public void setId(Integer _id) {this.id = _id; }

    public Integer getId(){return  this.id;}

    public void setExecStack(MyIntStack<IntStatement> execStack) {
        this.execStack = execStack;
    }



    public void setOut(MyIntList<Integer> out) {
        this.out = out;
    }

    public void setOriginalProgram(IntStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIntStack<IntStatement> getExecStack() {
        return execStack;
    }

    public MyIntStack< MyIntDict<String, Integer>> getSymTable() {
        return symTablestack;
    }

    public MyIntList<Integer> getOut() {
        return out;
    }

    public IntStatement getOriginalProgram() {
        return originalProgram;
    }

    public IntFileTable getFileTable() { return filetable; }

    public  IntHeap getHeap() { return heap; }

    public String toString(){
        String msg = "";
        msg += "Program state id: " + id.toString() + "\n";
        msg += execStack.toString() + "\n";
        msg += symTablestack.toString() + "\n";
        msg += out.toString() + "\n";
        msg += heap.toString() + "\n";
        msg += filetable.toString() + "\n";
        return msg;
    }

    public Boolean isNotCompleted(){
        return !execStack.empty();
    }

    public ProgState oneStep() throws MyException {

        if(execStack.empty()){
            throw new MyException("Stack is empty!");
        }
        IntStatement statement = execStack.pop();

        return statement.execute(this);
    }
}
