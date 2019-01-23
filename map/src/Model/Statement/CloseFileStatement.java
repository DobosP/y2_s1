package Model.Statement;

import Model.DataStructure.FileTableValue;
import Model.DataStructure.MyIDictionary;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements IStatement {
    private String fileId;

    public CloseFileStatement(final String fileId) {
        this.fileId = fileId;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, FileTableValue> fileTable = state.getFileTable();

        final Integer toClose = symTable.get(fileId);
        final FileTableValue fileTableValue = fileTable.get(toClose);
        final BufferedReader br = fileTableValue.getFileDescriptor();
        try {
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        symTable.remove(fileId);
        fileTable.remove(toClose);

        return null;
    }

    @Override
    public String toString() {
        return "Close file: " + fileId;
    }
}
