namespace DataStructure
{
    public interface MyIntStack<T>
    {
        void push(T val);
        T pop();
        bool empty();
        
        string ToString();
         
    }
}