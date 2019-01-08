using System.Collections.Generic;
using MyException;

namespace DataStructure
{
    public class MyDict<K, V> : MyIntDict<K, V>
    {
        Dictionary<K, V> hash;


        public MyDict(){
            hash = new Dictionary<K, V>();
        }

        public bool exists(K key) {
            return hash.ContainsKey(key);
        }

        public void add(K key, V val) {
            hash.Add(key, val);
        }

        public void remove(K key) {
            if(hash.ContainsKey(key)) {
                hash.Remove(key);
            }
        }

        public V get(K key){
            if (hash.ContainsKey(key)) {
                return hash[key];
            }
            else {
                throw new MyException.MyException("Element not found");
            }
        }

        public void update(K key, V val){
            if (hash.ContainsKey(key)) {
                hash[key] = val;
            }
            else {
                throw new MyException.MyException("Element not found");
            }
        }
        
        override public string ToString(){
        string printstr = "";
        printstr += "SymTable";
        foreach(KeyValuePair<K , V > item in hash)
            printstr += "\n" + item.Key.ToString() + "-->" + item.Value.ToString();
        return printstr;
    }
        
    }

}