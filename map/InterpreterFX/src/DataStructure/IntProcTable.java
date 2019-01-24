package DataStructure;


import model.IntStatement;

import java.util.List;
import java.util.Map;

public interface IntProcTable {

    Pair<List<String>, IntStatement> getValue(String name);
    void put(String name, Pair<List<String>, IntStatement> val);

    Map<String,Pair<List<String>, IntStatement>> getConten();
}
