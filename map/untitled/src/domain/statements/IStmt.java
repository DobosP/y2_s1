package domain.statements;

import domain.PrgState;
import exception.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    String toString();
}
