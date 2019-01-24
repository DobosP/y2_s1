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

    public T getFirst(){
        return first;
    }
    public V getSecond(){
        return  second;
    }

    @Override
    public String toString(){
        return first.toString() + " " + second.toString();
    }

}
