package Model.DataStructure;

import java.util.EmptyStackException;

public interface MyIStack<E> {

    boolean empty();

    E peek() throws EmptyStackException;

    E pop() throws EmptyStackException;

    E push(E tElem);

    int size();

    String toString();
}
