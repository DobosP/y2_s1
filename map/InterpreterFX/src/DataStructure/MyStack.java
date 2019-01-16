package DataStructure;

import java.util.Collections;
import java.util.Stack;
import java.util.ArrayList;

public class MyStack<T> implements MyIntStack<T> {

    Stack<T> stack;

    public MyStack(){
        stack = new Stack<T>();
    }

    @Override
    public void push(T val) {
        stack.push(val);
    }

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        String printstr = new String();
        printstr += "ExecStack:";
        ArrayList<T> array = new ArrayList<T>(stack);
        Collections.reverse(array);
        for(T e: array)
            printstr += "\n" +e.toString();
        return printstr;
    }
}
