using System;
using Controller;
namespace Interface
{
    public class AllStepsCommand : Command
    {
        private Controller.Controller ctr;
        public AllStepsCommand(String key, String desc,Controller.Controller ctr) : base(key, desc){
            this.ctr=ctr;
        }
        override public void execute() {
   
            System.Console.WriteLine("Index of Program :");
            String index = System.Console.ReadLine();
            try {
                ctr.AllSteps(Int32.Parse(index));
            }
            catch(MyException.MyException e){
                System.Console.WriteLine(e.getMessage());
            } 
        }

    }
}