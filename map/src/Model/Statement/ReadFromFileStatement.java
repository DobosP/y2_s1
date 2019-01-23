package Model.Statement;

import Model.DataStructure.FileTableValue;
import Model.DataStructure.MyIDictionary;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFromFileStatement implements IStatement {
    private String fileId;
    private String varName;

    public ReadFromFileStatement(final String fileId, final String varName) {
        this.fileId = fileId;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIDictionary<Integer, FileTableValue> fileTable = state.getFileTable();

        final Integer toReadFrom = symTable.get(fileId);
        final FileTableValue fileTableValue = fileTable.get(toReadFrom);
        final BufferedReader br = fileTableValue.getFileDescriptor();

        Integer readValue = 0;
        try {
            String line = br.readLine();
            if (line == null) {
                System.out.println("Reached end of file\n");
                return state;
            }
            if (line.length() != 0) {
                readValue = Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        if (symTable.containsKey(varName)) {
            symTable.replace(varName, readValue);
        } else {
            symTable.put(varName, readValue);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Read in var: " + varName + " from file: " + fileId;
    }
}
