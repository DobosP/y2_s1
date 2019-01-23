package Model.DataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeapTable <K, V> implements MyIDictionary<K, V> {
    Map<K, V> dictionary = new HashMap<K, V>();

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public V get(K key) {
        return dictionary.get(key);
    }

    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public V put(K key, V value) {
        return dictionary.put(key, value);
    }

    @Override
    public V remove(K key) {
        return dictionary.remove(key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public boolean containsValue(V value) {
        return dictionary.containsValue(value);
    }

    @Override
    public V replace(K key, V value) {
        return dictionary.replace(key, value);
    }

    @Override
    public Set<K> keySet() { return dictionary.keySet(); }

    @Override
    public String toString() {
        String result = "HeapTable: \n{\n";
        for (K key : dictionary.keySet()) {
            V value = dictionary.get(key);
            result += key.toString() + " -> " + value.toString() + "\n";
        }
        result += "}\n";
        return result;
    }

    @Override
    public void setContent(Map<K, V> newDictionary) {
        this.dictionary = newDictionary;
    }

    @Override
    public Map<K, V> getContent() {
        return this.dictionary;
    }

    @Override
    public Collection<V> values() {
        return this.dictionary.values();
    }
}

