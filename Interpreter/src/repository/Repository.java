package repository;

import DataStructure.MyList;
import ExceptionHandling.MyException;
import model.ProgState;
import DataStructure.MyIntList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IntRepository {

    MyIntList<ProgState> list;
    String file_name;

    public Repository(String _file_name) {
        list = new MyList<ProgState>();
        file_name = _file_name;
    }

    @Override
    public void addPrgState(ProgState prog) {
        list.add(prog);
    }

    @Override
    public ProgState getProgState(int index) throws MyException {
        try {
            return list.get(index);
        } catch (MyException e) {
            throw e;
        }
    }

    @Override
    public void logPrgStateExec(int index) throws MyException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(file_name, true)));
        }
        catch (IOException exp){
            throw new MyException("File not found");
        }
        logFile.write(list.get(index).toString());
        logFile.close();
    }
}