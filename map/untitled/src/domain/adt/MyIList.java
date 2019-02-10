package domain.adt;

import java.util.List;
import java.util.Queue;

public interface MyIList<T> {
    void add(T v);
    T pop();
    String toString();
    void setList(Queue<T> queue);
    Queue<T> getList();
}
