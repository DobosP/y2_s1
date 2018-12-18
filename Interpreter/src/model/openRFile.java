package model;

import DataStructure.IntFileTable;
import DataStructure.MyIntDict;
import DataStructure.Pair;
import ExceptionHandling.MyException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class openRFile implements IntStatement {

    String var_file_id;
    String filename;

    public openRFile(String _var_file_id, String _filename){
        var_file_id = _var_file_id;
        filename = _filename;
    }

    @Override
    public String toString(){
        return "openFile " +  filename + " in " + var_file_id;
    }



    @Override
    public ProgState execute(ProgState state) throws MyException {
        IntFileTable fileTable = state.getFileTable();
        if(fileTable.file_is_open(filename))
            throw new MyException("File is already open!");

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            throw new MyException("Invalid path");
        }
        int index = fileTable.add(new Pair(filename, reader));
        MyIntDict<String, Integer> symtable = state.getSymTable();
        symtable.add(var_file_id, index);
        return null;
    }
}
