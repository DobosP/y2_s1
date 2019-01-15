using models;
using DataStructure;
using System.IO;
using System;
namespace Repository
{
    public class Repository : IntRepository
    {
        MyIntList<ProgState> list;
        String file_name;

        public Repository(String _file_name) {
            list = new MyList<ProgState>();
            file_name = _file_name;
        }


        public void addPrgState(ProgState prog) {
            list.add(prog);
        }


        public void logPrgStateExec(int index) {
            StreamWriter logFile;
            try {
                logFile = new StreamWriter(file_name, true);
            }
            catch (IOException exp){
                throw new MyException.MyException("File not found");
            }
            logFile.Write(list.get(index).ToString());
            logFile.Close();
        }

       
        public ProgState getPrg(int index) {
            return list.get(index);
        }
    }
}