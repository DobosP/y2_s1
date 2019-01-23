package Model.Statement;

import Model.Exception.FileAlreadyOpenedException;
import Model.PrgState;

import java.io.FileNotFoundException;

public interface IStatement {
    String toString();
    PrgState execute(PrgState state) throws FileAlreadyOpenedException,
            FileNotFoundException;
}
