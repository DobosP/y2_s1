using DataStructure;
namespace models
{
    abstract public class Expression
    {
        abstract public int eval(MyIntDict<string, int> tab); 
    }
}