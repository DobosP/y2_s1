package DataStructure;

import ExceptionHandling.MyException;


import java.io.BufferedReader;
import java.util.Map;

public interface IntFileTable{
    boolean file_is_open(String file_name);
    int add(Pair<String, BufferedReader> val);
    void remove(int key);
    Pair<String, BufferedReader> get(int key) throws MyException;
    void update(int key, Pair <String, BufferedReader> val) throws MyException;
    boolean exists(int key);
    Map<Integer, Pair<String, BufferedReader>> getContent();
}
