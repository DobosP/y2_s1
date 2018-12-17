package DataStructure;

import ExceptionHandling.MyException;
import java.io.BufferedReader;

import java.util.Hashtable;
import java.util.Map;

public class FileTable implements IntFileTable{
    Hashtable<Integer, Pair<String, BufferedReader>> hash;
    int index;

    public FileTable(){
        hash = new Hashtable<Integer,Pair<String, BufferedReader>>();
        index = 0;
    }

    @Override
    public boolean file_is_open(String file_name) {
        for(Integer key: hash.keySet())
            if(hash.get(key).getfirst().equals(file_name))
                return true;
        return false;
    }

    @Override
    public int add(Pair<String, BufferedReader>  val) {
        hash.put(index, val);
        index += 1;
        return  index -1;
    }

    @Override
    public void remove(int key) {
        if(hash.containsKey(key)) {
            hash.remove(key);
        }
    }

    @Override
    public Pair<String, BufferedReader>  get(int key) throws MyException {
        if (hash.containsKey(key)) {
            return hash.get(key);
        }
        else {
            throw new MyException("File descriptor not found!");
        }
    }

    @Override
    public void update(int key, Pair<String, BufferedReader>  val) throws MyException {
        if (hash.containsKey(key)) {
            hash.replace(key, val);
        }
        else {
            throw new MyException("Element not found");
        }
    }

    @Override
    public boolean exists(int key) {
        return hash.containsKey(key);
    }

    @Override
    public Map<Integer, Pair<String, BufferedReader>> getContent() {
        return hash;
    }

    @Override
    public String toString(){
    String printstr = new String();
    printstr += "FileTable";
        for(Integer key: hash.keySet())
    printstr += "\n" + key.toString() + "-->" + hash.get(key).getfirst();
        return printstr;
    }
}
