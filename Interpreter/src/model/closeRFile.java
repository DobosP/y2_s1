package model;

import DataStructure.IntFileTable;
import DataStructure.MyIntDict;
import ExceptionHandling.MyException;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IntStatement {

    String exp_file_id;

    public closeRFile(String _exp_file_id){
        exp_file_id = _exp_file_id;
    }


    @Override
    public String toString(){
        return "closeFile " + exp_file_id;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException{
        MyIntDict<String, Integer> symtable = state.getSymTable();
        IntFileTable filetable = state.getFileTable();

        if(!symtable.exists(exp_file_id)){
            throw new MyException("File descriptor not found!");
        }
        int index_file = symtable.get(exp_file_id);
        if(!filetable.exists(index_file)){
            throw new MyException(("File descriptor does not exist!"));
        }
        BufferedReader reader = filetable.get(index_file).getsecond();

        try {
            reader.close();
        } catch (IOException e) {
            throw new MyException("File can not be close!");
        }
        filetable.remove(index_file);
        return null;
    }
}
