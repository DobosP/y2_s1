using System;
using System.IO;
using DataStructure;
namespace models
{
    public class readFile : IntStatement
    {
        String exp_file_id;
        String var_name;

        public readFile(String _exp_file_id, String _var_name){
            exp_file_id = _exp_file_id;
            var_name = _var_name;
        }


        override public String ToString(){
            return "readFile " + exp_file_id + " in " + var_name;
        }

        public ProgState execute(ProgState state) {
            MyIntDict<String, int> symtable = state.getSymTable();
            IntFileTable filetable = state.getFileTable();

            if(!symtable.exists(exp_file_id)){
                throw new MyException.MyException("File descriptor not found!");
            }
            int index_file = symtable.get(exp_file_id);
            if(!filetable.exists(index_file)){
                throw new MyException.MyException(("File descriptor does not exist!"));
            }
            StreamReader reader = filetable.get(index_file).Item2;
            String line;
            try {
                line = reader.ReadLine();
            } catch (IOException e) {
                line = "";
            }
            int value = 0;

            if(line == ""){
                value = Int32.Parse(line);
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
}