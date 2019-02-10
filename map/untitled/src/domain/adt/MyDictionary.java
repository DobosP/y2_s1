package domain.adt;

import exception.MyException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2>{
    HashMap<T1,T2> dictionary;

    public  MyDictionary() {dictionary=new HashMap<T1,T2>();}

    @Override
    public void add(T1 v1, T2 v2) {
        dictionary.put(v1,v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        dictionary.put(v1,v2);
    }

    @Override
    public T2 lookup(T1 id) throws MyException {
        //get-ul cauta cheia si returneaza valoarea. Daca nu exista cheia, returneaza NULL
        if(dictionary.get(id)!=null)
        {
            return dictionary.get(id);
        }
        throw new MyException("Couldn't find the given id.");
    }

    public List<T1> getKeys()
    {
        List<T1> list=new LinkedList<>();
        for(T1 key:dictionary.keySet())
        {
            list.add(key);
        }
        return list;
    }

    public List<T2> getValues()
    {
        List<T2> list=new LinkedList<>();
        for(T1 key:dictionary.keySet())
        {
            T2 value=dictionary.get(key);
            list.add(value);
        }
        return list;
    }

    @Override
    public boolean isDefined(String id) {
        if(dictionary.get(id)!=null)
            return true;
        return false;
    }

    public int get_size()
    {
        return dictionary.size();
    }

    public HashMap<T1,T2> getContent(){
        return dictionary;
    }

    public void remove(T1 elem){
        dictionary.remove(elem);
    }

    public String toString()
    {
        String infoDict="";
        for(HashMap.Entry<T1,T2> e:dictionary.entrySet())
        {
            infoDict=infoDict+"Key: "+e.getKey().toString()+", Value: "+e.getValue().toString()+"\n";
        }
        return infoDict;
    }

    public HashMap<T1,T2> getDictionary() {return dictionary;}
}
