using System;
using System.IO;
using DataStructure;
using System.Collections.Generic;
namespace models
{
    public class openRFile : IntStatement
    {
        String var_file_id;
        String filename;

        public openRFile(String _var_file_id, String _filename){
            var_file_id = _var_file_id;
            filename = _filename;
        }


        override public String ToString(){
            return "openFile " +  filename + " in " + var_file_id;
        }



        public ProgState execute(ProgState state) {
            IntFileTable fileTable = state.getFileTable();
            if(fileTable.file_is_open(filename))
                throw new MyException.MyException("File is already open!");

            StreamReader reader;
            try{
                reader = new StreamReader(filename);
            } catch (IOException e) {
                throw new MyException.MyException("Invalid path");
            }
            int index = fileTable.add(new Tuple<String, StreamReader>(filename, reader));
            MyIntDict<String, int> symtable = state.getSymTable();
            symtable.add(var_file_id, index);
            return null;
        }
    }
}