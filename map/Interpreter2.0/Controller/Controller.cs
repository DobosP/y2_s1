using System;
using DataStructure;
using models;
using Repository;
namespace Controller
{
    public class Controller
    {
         private IntRepository rep;

        public Controller(IntRepository _rep){
            rep = _rep;
        }


        public void addPrgState(ProgState state){
            rep.addPrgState(state);
        }

        public ProgState OneStep(int index)  {
            ProgState state;
            try {
                state = rep.getPrg(index);
            }
            catch (MyException.MyException e){
                throw e;
            }


            MyIntStack<IntStatement> stack = state.getExecStack();
            if(stack.empty()){
                throw new MyException.MyException("Stack is empty!");
            }
            IntStatement statement = stack.pop();
            ProgState newstate;
            try {
            newstate = statement.execute(state);
            }
            catch (MyException.MyException e){
                throw e;
            }
        

            return newstate;

        }

        public void AllSteps(int index)  {
            try {
                while (true) {
                    
                    this.OneStep(index);
                    rep.logPrgStateExec(index);
                }
            } catch (MyException.MyException e) {
                throw e;
            } 

        }
    }
}