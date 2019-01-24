package model;

import DataStructure.IntFileTable;
import DataStructure.MyIntDict;
import DataStructure.Pair;
import ExceptionHandling.MyException;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IntStatement{
    String exp_file_id;
    String var_name;

    public readFile(String _exp_file_id, String _var_name){
        exp_file_id = _exp_file_id;
        var_name = _var_name;
    }

    @Override
    public String toString(){
        return "readFile " + exp_file_id + " in " + var_name;
    }

    @Override
    public ProgState execute(ProgState state) throws MyException {
        MyIntDict<String, Integer> symtable = state.getSymTable().top();
        IntFileTable filetable = state.getFileTable();

        if(!symtable.exists(exp_file_id)){
            throw new MyException("File descriptor not found!");
        }
        int index_file = symtable.get(exp_file_id);
        if(!filetable.exists(index_file)){
            throw new MyException(("File descriptor does not exist!"));
        }
        BufferedReader reader = filetable.get(index_file).getsecond();
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            line = "";
        }
        int value = 0;

        if(!line.equals("")){
            value = Integer.parseInt(line);
        }

        if(symtable.exists(var_name)){
            symtable.update(var_name, value);
        }
        else {
            symtable.add(var_name, value);
        }
        return null;
    }
}
