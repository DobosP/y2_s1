using System;
namespace Interface
{
    public class ExitCommand : Command
    {
        public ExitCommand(String key, String desc) :  base(key, desc){
        
    }

        override public void execute() {
            Environment.Exit(0);
        }
    }
}