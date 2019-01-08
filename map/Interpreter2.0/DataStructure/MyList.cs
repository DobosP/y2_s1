using System.Collections.Generic;
using MyException;
namespace DataStructure
{
    public class MyList<T> : MyIntList<T>
    {
    List<T> list;

    public MyList(){
        list = new List<T>();
    }

    public void add(T val) {
        list.Add(val);
    }

    public T get(int index) {
        if(index < list.Count && index >= 0){
            return list[index];
        }
        else {
            throw new MyException.MyException("Index out of range!");
        }

    }

    public void remove(int index){
        if(index < list.Count && index >= 0){
            list.RemoveAt(index);
        }
        else {
            throw new MyException.MyException("Index out of range!");
        }
    }

    public int size() {
        return list.Count;
    }


    override public string ToString(){
        string printstr = "";
        printstr += "Out";
        foreach(T e in list)
            printstr += "\n" + e.ToString();
        return printstr;
    }
        
    }
}