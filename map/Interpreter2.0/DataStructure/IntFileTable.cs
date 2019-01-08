using System;
using System.IO;
namespace DataStructure
{
    public interface IntFileTable
    {
        bool file_is_open(string file_name);
        int add(Tuple<String, StreamReader> val);
        void remove(int key);
        Tuple<String, StreamReader> get(int key);
        void update(int key, Tuple <String, StreamReader> val);
        bool exists(int key);
    }
}