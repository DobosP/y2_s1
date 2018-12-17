package DataStructure;

import ExceptionHandling.MyException;

import java.util.Hashtable;
import java.util.Map;

public class Heap implements IntHeap {
    Map<Integer, Integer> hash;
    int index;

    public Heap(){
        hash = new Hashtable<Integer, Integer>();
        index = 1;
    }


    @Override
    public void remove(int key) {
        if(hash.containsKey(key)) {
            hash.remove(key);
        }
    }

    @Override
    public int add(int val) {
        hash.put(index, val);
        index += 1;
        return  index -1;
    }

    @Override
    public int get(int key) throws MyException {
        if (hash.containsKey(key)) {
            return hash.get(key);
        }
        else {
            throw new MyException("Memory address not found!");
        }
    }

    @Override
    public void update(int key, int val) throws MyException {
        if (hash.containsKey(key)) {
            hash.replace(key, val);
        }
        else {
            throw new MyException("Memory address not found!");
        }
    }

    @Override
    public boolean exists(int key) {
        return hash.containsKey(key);
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return hash;
    }

    @Override
    public void setContent(Map<Integer, Integer> _hash) {
        this.hash = _hash;
    }

    @Override
    public String toString(){
        String printstr = new String();
        printstr += "Heap";
        for(Integer key: hash.keySet())
            printstr += "\n" + key.toString() + "-->" + hash.get(key).toString();
        return printstr;
    }
}
