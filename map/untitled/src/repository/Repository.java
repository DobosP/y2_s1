package repository;

import domain.PrgState;
import domain.adt.MyList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Queue;

public class Repository implements IRepository{

    MyList<PrgState> myPrgStates;
    String fisier;

    public Repository(String fisier) {myPrgStates=new MyList<PrgState>() ;
        this.fisier=fisier;
    }

    public void logPrgStateExec(PrgState newPrgState)
    {
        PrintWriter logFile=null;
        try {
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(fisier, true)));
            logFile.println(newPrgState.toString());
        }catch (IOException e)
        {System.err.println("Eroare scriere BW "+e);}
        finally {
            if(logFile!=null)
                logFile.close();
        }

    }

    @Override
    public void addPrg(PrgState newPrg) {
        myPrgStates.add(newPrg);
    }

    public MyList<PrgState> getPrgList()
    {
        return myPrgStates;
    }

    public void setPrgList(MyList<PrgState> newPrgList)
    {
        myPrgStates=newPrgList;
    }

    @Override
    public PrgState getCrtPrg() {
        return myPrgStates.pop();
    }
}
