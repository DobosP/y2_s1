package Model;

import Model.DataStructure.FileTableValue;
import Model.DataStructure.MyIDictionary;
import Model.DataStructure.MyIList;
import Model.DataStructure.MyIStack;
import Model.Exception.FileAlreadyOpenedException;
import Model.Statement.IStatement;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class PrgState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, Integer> symTable;
    MyIList<Integer> out;
    MyIDictionary<Integer, FileTableValue> fileTable;
    MyIDictionary<Integer, Integer> heapTable;
    int id;

    public PrgState(MyIStack<IStatement> stck, MyIDictionary<String, Integer> symTable,
                    MyIList<Integer> out, MyIDictionary<Integer, FileTableValue> fileTable,
                    MyIDictionary<Integer, Integer> heapTable, int id) {
        this.exeStack = stck;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
//        this.originalProgram = deepCopy(prg);
        this.heapTable = heapTable;
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<Integer, Integer> getHeapTable() {
        return heapTable;
    }

    public void setHeapTable(MyIDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public MyIDictionary<Integer, FileTableValue> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<Integer, FileTableValue> fileTable) {
        this.fileTable = fileTable;
    }

    public boolean isNotCompleted() {
        return !exeStack.empty();
    }

    public PrgState oneStep() {
        try {
            MyIStack<IStatement> stck = this.getExeStack();
            IStatement stmt = stck.pop();
            return stmt.execute(this);
        } catch (FileNotFoundException | FileAlreadyOpenedException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } finally {
            this.getHeapTable().setContent(this.conservativeGarbageCollector(
                    this.getSymTable().getContent().values(),
                    this.getHeapTable().getContent()
            ));
            System.out.println(this);
        }
        return null;
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                               Map<Integer, Integer> heapTable) {
        return heapTable.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String toString() {
        return "PrgState: " + id + "\n" + exeStack.toString() + symTable.toString() + fileTable.toString() + out.toString() + heapTable.toString();
    }
}
