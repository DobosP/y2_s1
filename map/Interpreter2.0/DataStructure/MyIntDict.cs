namespace DataStructure
{
    public interface MyIntDict < K, V >
        {
        bool exists(K key);
        void add(K key, V val);
        void remove(K key);
        V get(K key);
        void update(K key, V val);

        string ToString();

    }
}