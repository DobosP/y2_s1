package DataStructure;

public class Pair<T, V> {
    private T first;
    private V second;

    public Pair(T _first,  V _second){
        first = _first;
        second = _second;
    }

    public T getfirst(){
        return first;
    }
    public V getsecond(){
        return  second;
    }

}
