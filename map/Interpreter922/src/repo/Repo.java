package repo;

import model.ProgState;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    List<ProgState> myProgState;
    public Repo() {
        myProgState = new ArrayList<>();
    }

    public void addPrg(ProgState progState) {
        myProgState.add(progState);
    }
}
