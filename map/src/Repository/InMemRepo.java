package Repository;

import Model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class InMemRepo implements IRepo {
    private List<PrgState> programs;
    private String logFilePath;
    private PrintWriter logFileWriter;

    public InMemRepo(final List<PrgState> programs, final String logFilePath) {
        this.programs = programs;
        this.logFilePath = logFilePath;
        try {
            this.logFileWriter = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public List<PrgState> getPrgList() {
        return this.programs;
    }

    public PrgState getProgramState(final int id) {
        for (int i = 0; i < this.programs.size(); i++) {
            if (id == this.programs.get(i).getId()) {
                return this.programs.get(i);
            }
        }

        return null;
    }

    public void setPrgList(final List<PrgState> stateList) {
        this.programs = stateList;
    }

    public void logPrgStateExec(final PrgState state) {
        logFileWriter.println(state);
        logFileWriter.flush();
    }
}
