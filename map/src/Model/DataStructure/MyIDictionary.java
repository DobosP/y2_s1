package Model.DataStructure;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<K, V> {

    int size();

    boolean isEmpty();

    V get(final K key);

    boolean containsKey(K key);

    V put(final K key, final V value);

    V remove(final K key);

    void clear();

    boolean containsValue(final V value);

    V replace(final K key, final V value);

    Set<K> keySet();

    String toString();

    void setContent(Map<K, V> newDictionary);

    Map<K, V> getContent();

    Collection<V> values();
}
