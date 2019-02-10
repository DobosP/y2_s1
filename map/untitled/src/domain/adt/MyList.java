package domain.adt;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyList<T> implements MyIList<T>{

    Queue<T> list;

    public MyList() {list=new LinkedList<T>();}

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.poll();
    }

    public String toString() {
        String infoList ="";
        for(T e:list)
        {
            infoList=infoList+ e.toString()+"\n";
        }
        return infoList;
    }

    public Queue<T> getList() {return list;}

    public void setList(Queue<T> queue)
    {
        this.list=queue;
    }
}
