package model.statement;

import model.ProgState;
import util.ISymTable;

public interface IStatement {
    public ProgState execute(ProgState p);

    int evaluate(ISymTable symTable);
}
