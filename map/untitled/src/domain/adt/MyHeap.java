package domain.adt;

import exception.MyException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T1,T2> implements MyIHeap<T1,T2>{

    private HashMap<T1,T2> heap;

    public MyHeap(){this.heap=new HashMap<>();}
    @Override
    public void add(T1 e1, T2 e2) {
        heap.put(e1,e2);
    }

    @Override
    public void update(T1 e1, T2 e2) {
        heap.put(e1,e2);
    }

    @Override
    public T2 lookup(T1 id) throws Exception {
        if(heap.get(id)!=null)
            return heap.get(id);
        throw new MyException("Couldn't find the given id.");
    }

    @Override
    public boolean isDefined(Integer id) {
        if(heap.get(id)!=null)
            return true;
        return false;
    }

    public int get_size()
    {
        return heap.size();
    }

    public void setContent(Map<T1,T2> e)
    {
        heap=(HashMap<T1,T2>) e;
    }

    public HashMap<T1,T2> getContent(){
        return heap;
    }

    public String toString()
    {
        String infoHeap="";
        for(HashMap.Entry<T1,T2> e:heap.entrySet())
        {
            infoHeap=infoHeap+"Key:"+e.getKey().toString()+" Value:"+e.getValue().toString()+"\n";
        }
        return infoHeap;
    }
}
