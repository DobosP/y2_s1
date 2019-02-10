package repository;

import domain.PrgState;
import domain.adt.MyList;


public interface IRepository {
    void addPrg(PrgState newPrg);
    void logPrgStateExec(PrgState newPrgState);
    PrgState getCrtPrg();
    MyList<PrgState> getPrgList();
    void setPrgList(MyList<PrgState> newPrgList);
}
