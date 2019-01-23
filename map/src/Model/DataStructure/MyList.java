package Model.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class MyList<E> implements MyIList<E> {
    List<E> list = new ArrayList<E>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(final E tElem) throws ClassCastException, NullPointerException {
        try {
            return list.contains(tElem);
        } catch (ClassCastException | NullPointerException e) {
            throw e;
        }
    }

    @Override
    public boolean add(final E tElem) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException {
        try {
            return list.add(tElem);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public boolean remove(final E tElem) throws ClassCastException, NullPointerException, UnsupportedOperationException {
        try {
            return list.remove(tElem);
        } catch (ClassCastException | NullPointerException | UnsupportedOperationException e) {
            throw e;
        }
    }

    @Override
    public void clear() throws UnsupportedOperationException {
        try {
            list.clear();
        } catch (UnsupportedOperationException e) {
            throw e;
        }
    }

    @Override
    public E get(final int index) throws IndexOutOfBoundsException {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public E set(final int index, final E tElem) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
        try {
            return list.set(index, tElem);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public void add(final int index, final E tElem) throws UnsupportedOperationException, ClassCastException, NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
        try {
            list.add(index, tElem);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e){
            throw e;
        }
    }

    public List<E> getList() {
        return this.list;
    }

    @Override
    public E remove(final int index) throws UnsupportedOperationException, IndexOutOfBoundsException {
        try {
            return list.remove(index);
        } catch (UnsupportedOperationException | IndexOutOfBoundsException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        String result = "Out: \n{\n";
        for (E elem : list) {
            result += elem.toString() + "\n";
        }
        result += "}\n";
        return result;
    }
}