
namespace DataStructure
{
    public interface MyIntList<T>
    {
        void add(T val);
        T get(int index);
        void remove(int index);
        int size();

        string ToString();
    }
}