package model;

import DataStructure.*;

public class ProgState {
    MyIntStack<IntStatement> execStack;
    MyIntDict<String, Integer> symTable;
    MyIntList<Integer> out;
    IntStatement originalProgram;
    IntFileTable filetable;
    IntHeap heap;
    public ProgState(MyIntStack<IntStatement> stack,
              MyIntDict<String, Integer> dict,
              MyIntList<Integer> list,
              IntFileTable _fileTable,
              IntHeap _heap,
              IntStatement statement){
        execStack = stack;
        symTable = dict;
        out = list;
        filetable = _fileTable;
        heap = _heap;
        originalProgram = statement;
        execStack.push(statement);
    }

    public void setExecStack(MyIntStack<IntStatement> execStack) {
        this.execStack = execStack;
    }

    public void setSymTable(MyIntDict<String, Integer> symTable) {
        this.symTable = symTable;
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

    public MyIntDict<String, Integer> getSymTable() {
        return symTable;
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

        msg += execStack.toString() + "\n";
        msg += symTable.toString() + "\n";
        msg += out.toString() + "\n";
        msg += heap.toString() + "\n";
        msg += filetable.toString();
        return msg;
    }
}
