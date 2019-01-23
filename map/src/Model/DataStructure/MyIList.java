package Model.DataStructure;

import java.util.List;

public interface MyIList<E> {

    int size();

    boolean isEmpty();

    boolean contains(final E tElem) throws ClassCastException,
             NullPointerException;

    boolean add(final E tElem) throws UnsupportedOperationException,
                                ClassCastException,
                                NullPointerException,
                                IllegalArgumentException;

    boolean remove(final E tElem) throws ClassCastException,
                                   NullPointerException,
                                   UnsupportedOperationException;

    void clear() throws UnsupportedOperationException;

    E get(final int index) throws IndexOutOfBoundsException;

    E set(final int index, final E tElem) throws UnsupportedOperationException,
                                            ClassCastException,
                                                   NullPointerException,
                                                   IllegalArgumentException,
                                                   IndexOutOfBoundsException;
    void add(final int index, final E tElem) throws UnsupportedOperationException,
            ClassCastException,
            NullPointerException,
            IllegalArgumentException,
            IndexOutOfBoundsException;

    E remove(final int index) throws UnsupportedOperationException,
                                     IndexOutOfBoundsException;

    String toString();

    List<E> getList();

}
