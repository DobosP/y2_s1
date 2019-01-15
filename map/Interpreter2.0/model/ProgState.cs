using System;
using DataStructure;
namespace models
{
    public class ProgState
    {
        MyIntStack<IntStatement> execStack;
        MyIntDict<String, int> symTable;
        MyIntList<int> output;
        IntStatement originalProgram;
        IntFileTable filetable;

        public ProgState(
                MyIntStack<IntStatement> stack,
                MyIntDict<String, int> dict,
                MyIntList<int> list,
                IntFileTable _fileTable,
                IntStatement statement){
            execStack = stack;
            symTable = dict;
            output = list;
            filetable = _fileTable;
            originalProgram = statement;
            execStack.push(statement);
        }




        public MyIntStack<IntStatement> getExecStack() {
            return execStack;
        }

        public MyIntDict<String, int> getSymTable() {
            return symTable;
        }

        public MyIntList<int> getOut() {
            return output;
        }

        public IntStatement getOriginalProgram() {
            return originalProgram;
        }

        public IntFileTable getFileTable() { return filetable; }

       

        override public String ToString(){
            String msg = "";
            msg += "Program state \n";
            msg += execStack.ToString() + "\n";
            msg += symTable.ToString() + "\n";
            msg += output.ToString() + "\n";
            msg += filetable.ToString();
            return msg;
        }

    }
}