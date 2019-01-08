using System;
using System.IO;
using System.Collections.Generic;
using MyException;

namespace DataStructure
{
    public class FileTable : IntFileTable
    {
        Dictionary<int, Tuple<String, StreamReader>> hash;
        int index;

        public FileTable(){
            hash = new Dictionary<int, Tuple<String, StreamReader>>();
            index = 0;
        }

        public bool file_is_open(String file_name) {
            foreach(KeyValuePair<int, Tuple<String, StreamReader>> item in hash)
                if(item.Value.Item1 == file_name)
                    return true;
            return false;
        }

        public int add(Tuple<String, StreamReader>  val) {
            hash[index] = val;
            index += 1;
            return  index -1;
        }

        public void remove(int key) {
            if(hash.ContainsKey(key)) {
                hash.Remove(key);
            }
        }
        public Tuple<String, StreamReader>  get(int key)  {
            if (hash.ContainsKey(key)) {
                return hash[key];
            }
            else {
                throw new MyException.MyException("File descriptor not found!");
            }
        }


        public void update(int key, Tuple<String, StreamReader>  val) {
            if (hash.ContainsKey(key)) {
                hash[key] = val;
            }
            else {
                throw new MyException.MyException("Element not found");
            }
        }

        public bool exists(int key) {
            return hash.ContainsKey(key);
        }

        override public String ToString(){
        String printstr = "";
        printstr += "FileTable";
            foreach(KeyValuePair<int, Tuple<String, StreamReader>> item in hash)
        printstr += "\n" + item.Key.ToString() + "-->" + item.Value.Item1;
            return printstr;
        }
         
    }
}