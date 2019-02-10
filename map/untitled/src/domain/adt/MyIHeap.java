package domain.adt;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<T1,T2> {
    void add(T1 e1,T2 e2);
    void update(T1 e1,T2 e2);
    T2 lookup(T1 id) throws Exception;
    boolean isDefined(Integer id);
    String toString();
    void setContent(Map<T1,T2> e);
    HashMap<T1,T2> getContent();
    int get_size();
}
