package Repository;

import Model.PrgState;

import java.util.List;

public interface IRepo {

    List<PrgState> getPrgList();

    void setPrgList(final List<PrgState> stateList);

    void logPrgStateExec(PrgState state);

    public PrgState getProgramState(final int id);
}