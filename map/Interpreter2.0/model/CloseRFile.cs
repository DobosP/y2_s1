using System;
using System.IO;
using DataStructure;
namespace models
{
    public class closeRFile : IntStatement
    {
        String exp_file_id;

        public closeRFile(String _exp_file_id){
            exp_file_id = _exp_file_id;
        }



        override public String ToString(){
            return "closeFile " + exp_file_id;
        }

         public ProgState execute(ProgState state){
            MyIntDict<String, int> symtable = state.getSymTable();
            IntFileTable filetable = state.getFileTable();

            if(!symtable.exists(exp_file_id)){
                throw new MyException.MyException("File descriptor not found!");
            }
            int index_file = symtable.get(exp_file_id);
            if(!filetable.exists(index_file)){
                throw new MyException.MyException("File descriptor does not exist!");
            }
            StreamReader reader = filetable.get(index_file).Item2;

            try {
                reader.Close();
            } catch (IOException e) {
                throw new MyException.MyException("File can not be close!");
            }
            filetable.remove(index_file);
            return null;
        }
    }
}