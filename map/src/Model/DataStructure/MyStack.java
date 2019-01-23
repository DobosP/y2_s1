package Model.DataStructure;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class MyStack<E> implements MyIStack<E> {
    Stack<E> stack = new Stack<E>();

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public E peek() throws EmptyStackException {
        try {
            return stack.peek();
        } catch (EmptyStackException e) {
            throw e;
        }
    }

    @Override
    public E pop() throws EmptyStackException {
        try {
            return stack.pop();
        } catch (EmptyStackException e) {
            throw e;
        }
    }

    @Override
    public E push(E tElem) {
        return stack.push(tElem);
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        ArrayList<E> revArr = new ArrayList<E>();
        String result = "ExeStack: \n[\n";
        for (E elem : stack) {
            revArr.add(elem);
        }

        for (int i = revArr.size() - 1; i >= 0; i--) {
            E elem = revArr.get(i);
            result += elem.toString();
            result += "\n";
        }
        result += "]\n";
        return result;
    }
}