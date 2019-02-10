package domain.adt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    Stack<T> stack;

    public MyStack() {stack=new Stack<T>();}

    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public String toString(){
        String infoStack="";
        for(T e:stack)
        {
            infoStack=infoStack+e.toString()+"\n";
        }
        return infoStack;
    }

    public Stack<T> getStack() {return stack;}
}
