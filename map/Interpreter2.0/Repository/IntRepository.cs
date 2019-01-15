using models;
namespace Repository
{
    public interface IntRepository
    {
        void addPrgState(ProgState prog);
        void logPrgStateExec(int index);
        ProgState getPrg(int index); 
    }
}