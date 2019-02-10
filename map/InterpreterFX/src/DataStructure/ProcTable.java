package DataStructure;

import model.IntStatement;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ProcTable implements IntProcTable {
    Map<String, Pair<List<String>, IntStatement>> hash;

    public ProcTable(){
        hash = new Hashtable<>();
    }



    @Override
    public java.lang.String toString(){
        java.lang.String printstr = new java.lang.String();
        printstr += "ProgTable";
        for(java.lang.String key: hash.keySet()) {
            java.lang.String  itstirng;
            itstirng = key + "(";
            for(java.lang.String name: hash.get(key).getFirst() )
                itstirng += name + ",";
            itstirng = itstirng.substring(0,itstirng.length() - 1) + ") " + hash.get(key).getSecond().toString();
            printstr += "\n" + itstirng;

        }
        return printstr;
    }


    @Override
    public Pair<List<java.lang.String>, IntStatement> getValue(java.lang.String name) {
        return hash.get(name);
    }

    @Override
    public void put(java.lang.String name, Pair<List<java.lang.String>, IntStatement> val) {
        hash.put(name ,val);
    }

    @Override
    public Map<String, Pair<List<String>, IntStatement>> getConten() {
        return hash;
    }

    @Override
    public boolean exists(String name) {
        return hash.containsKey(name);
    }
}
