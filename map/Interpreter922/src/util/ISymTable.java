package util;

public interface ISymTable {
    public void add(String name, int var);
    public boolean find(String name);
    public int getValue(String name);
    public void setValue(String name, int var);
}
