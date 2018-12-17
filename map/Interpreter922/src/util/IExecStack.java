package util;

import model.statement.IStatement;

public interface IExecStack {
    public void push(IStatement stm);
    public IStatement pop();
    public boolean isEmpty();
}
