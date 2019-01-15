using System.Collections.Generic;
using System;
namespace Interface
{
    public class TextMenu
    {
            private Dictionary<String, Command> commands;
            public TextMenu(){ commands=new Dictionary<String, Command>(); }
            public void addCommand(Command c){ commands[c.getKey()] = c;}
            private void printMenu(){
                foreach(Command com in commands.Values){
                    String line=String.Format("{0} : {1}", com.getKey(), com.getDescription());
                    System.Console.WriteLine(line);
                }
            }
            public void show(){
                while(true){
                    printMenu();
                    System.Console.WriteLine("Input the option: ");
                    String key=System.Console.ReadLine();
                    Command com=commands[key];
                    if (com==null){
                        System.Console.WriteLine("Invalid Option");
                        continue; }
                    com.execute();
                }
            }
        
    }
}