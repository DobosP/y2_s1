package DataStructure;

import java.util.Stack;

public interface MyIntStack<T> {
    void push(T val);
    T pop();
    boolean empty();
    T top();
    Stack<T> getStack();
}
