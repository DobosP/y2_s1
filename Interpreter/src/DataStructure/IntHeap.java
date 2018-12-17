package DataStructure;

import ExceptionHandling.MyException;

import java.io.BufferedReader;
import java.util.Map;

public interface IntHeap {
    void remove(int key);
    int add(int val);
    int get(int key) throws MyException;
    void update(int key, int val) throws MyException;
    boolean exists(int key);
    Map<Integer, Integer> getContent();
    void setContent(Map<Integer, Integer> _hash);
}
