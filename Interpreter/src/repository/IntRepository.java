package repository;
import ExceptionHandling.MyException;
import model.ProgState;

import java.io.IOException;


public interface IntRepository {
    void addPrgState(ProgState prog);
    ProgState getProgState(int index) throws MyException;
    void logPrgStateExec(int index) throws  MyException;
}
