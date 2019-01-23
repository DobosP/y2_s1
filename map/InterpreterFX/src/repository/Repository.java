package repository;

import DataStructure.MyList;
import ExceptionHandling.MyException;
import model.ProgState;
import DataStructure.MyIntList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
    public void logPrgStateExec(ProgState prg) throws MyException {
        PrintWriter logFile;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(file_name, true)));
        }
        catch (IOException exp){
            throw new MyException("File not found");
        }
        logFile.write(prg.toString());
        logFile.close();
    }

    @Override
    public List<ProgState> getPrgList() {
        return list.getAll();
    }

    @Override
    public void setPrgList(List<ProgState> new_list) {
        list.setList(new_list);
    }

    @Override
    public ProgState getProgState(int id) {
        for(ProgState state: this.list.getAll()){
            if(state.getId() == id)
                return  state;
        }
        return null;
    }


}