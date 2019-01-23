package Model.DataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyFileTable<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> dictionary = new HashMap<K, V>();

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean isEmpty() {
        return dictionary.isEmpty();
    }

    @Override
    public boolean containsKey(final K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public V put(final K key, final V fileTableValue) {
        return dictionary.put(key, fileTableValue);
    }

    @Override
    public V remove(final K key) {
        return dictionary.remove(key);
    }

    @Override
    public void clear() {
        dictionary.clear();
    }

    @Override
    public boolean containsValue(final V fileTableValue) {
        return dictionary.containsValue(fileTableValue);
    }

    @Override
    public V replace(final K key, final V fileTableValue) {
        return dictionary.replace(key, fileTableValue);
    }

    @Override
    public V get(final K key) {
        return dictionary.get(key);
    }

    @Override
    public Set<K> keySet() {
        return dictionary.keySet();
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

    @Override
    public String toString() {
        String result = "File Table: \n{\n";
        for (K key : dictionary.keySet()) {
            V value = dictionary.get(key);
            result += key.toString() + " -> " + value.toString() + "\n";
        }
        result += "}\n";

        return result;
    }
}
