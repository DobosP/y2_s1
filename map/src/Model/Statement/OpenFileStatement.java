package Model.Statement;

import Model.DataStructure.FileTableValue;
import Model.DataStructure.Incrementor;
import Model.DataStructure.MyIDictionary;
import Model.Exception.FileAlreadyOpenedException;
import Model.PrgState;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements IStatement {
    private String fileId;
    private String filename;

    public OpenFileStatement(final String fileId, final String filename) {
        this.fileId = fileId;
        this.filename = filename;
    }


    @Override
    public PrgState execute(PrgState state) throws FileAlreadyOpenedException,
                                                   FileNotFoundException {
        MyIDictionary<Integer, FileTableValue> fileTable = state.getFileTable();
        MyIDictionary<String, Integer> symTable = state.getSymTable();

        boolean exists = false;
        for (Integer key : fileTable.keySet()) {
            FileTableValue value = fileTable.get(key);
            if (filename.equals(value.getFilename())) {
                exists = true;
                break;
            }
        }

        if (exists) {
            throw new FileAlreadyOpenedException("This file was already opened");
        }

        try {
            FileReader in = new FileReader(filename);
            BufferedReader br = new BufferedReader(in);
            Integer fileIntegerId = new Integer(Incrementor.getNewFileId());
            FileTableValue fileTableValue = new FileTableValue(filename, br);
            fileTable.put(fileIntegerId, fileTableValue);
            symTable.put(fileId, fileIntegerId);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return null;
    }

    @Override
    public String toString() {
        return "OpenFile " + fileId + " " + filename;
    }
}
