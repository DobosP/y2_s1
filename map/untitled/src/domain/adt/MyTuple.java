package domain.adt;

public class MyTuple<T1,T2> {
    T1 arg1;
    T2 arg2;

    public MyTuple(T1 arg1,T2 arg2)
    {
        this.arg1=arg1;
        this.arg2=arg2;
    }

    public T1 getFirst() {return arg1;}

    public T2 getSecond() {return arg2;}

    public String toString() {return arg1+" "+arg2;}
}
