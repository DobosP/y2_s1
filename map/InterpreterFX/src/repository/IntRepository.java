package repository;
import DataStructure.MyIntList;
import ExceptionHandling.MyException;
import model.ProgState;

import java.io.IOException;
import java.util.List;


public interface IntRepository {
    void addPrgState(ProgState prog);
    void logPrgStateExec(ProgState prg) throws  MyException;
    List<ProgState> getPrgList();
    void setPrgList(List <ProgState> prg);
    ProgState getProgState(int id);
}
