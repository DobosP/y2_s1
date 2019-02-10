package controller;

import domain.PrgState;
import domain.adt.*;
import domain.statements.IStmt;
import exception.MyException;
import repository.IRepository;
import repository.Repository;

import javax.swing.plaf.InputMapUIResource;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    IRepository myRepository;
    ExecutorService executor;

    public Controller(Repository myRepository)
    {
        this.myRepository=myRepository;
    }

    public void addProgram(PrgState newPrg)
    {
        myRepository.addPrg(newPrg);
    }

    public IRepository getRepo() {return myRepository;}


    public PrgState closeFillesGarbageCollector(PrgState prg)
    {
        MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable=prg.getFileTable();

        fileTable.getValues().stream().flatMap(pair ->
        {
            try {
                pair.getSecond().close();
            } catch (IOException e) {
                System.out.println("Some errors occured when closing the files!");
            }
            return null;
        });
        for(Integer k:fileTable.getKeys())
        {
            fileTable.remove(k);
        }
        return prg;
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream().
                filter(e->symTableValues.contains(e.getKey())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        prgList.forEach(prg ->myRepository.logPrgStateExec(prg));
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        //System.out.println("aici");
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                catch(Exception e) { }
                    return null;
                })
                .filter(p -> p!=null)
                .collect(Collectors.toList());

        //System.out.println("dupa");

        prgList.addAll(newPrgList);
        prgList.forEach(prg ->myRepository.logPrgStateExec(prg));

        Queue<PrgState> newqueue= new LinkedList<PrgState>(prgList);
        MyList<PrgState> newprgList=new MyList<PrgState>();
        newprgList.setList(newqueue);

        myRepository.setPrgList(newprgList);
        System.out.println(myRepository.getPrgList().toString());
    }

    public void stepOnce() {
        System.out.println("Step Once!");
        executor = Executors.newFixedThreadPool(2);

        //System.out.println(myRepository.getPrgList().getList().size());
        List<PrgState> prgList = removeCompletedPrg((List<PrgState>) myRepository.getPrgList().getList());
        //System.out.println("in allstep\n");
        //System.out.println(prgList.size());
        if (prgList.size() > 0) {
            try {
                for (int i = 0; i < prgList.size(); i++)
                    prgList.get(i).getHeap().setContent(conservativeGarbageCollector(
                            prgList.get(i).getSymTable().getContent().values(),
                            prgList.get(i).getHeap().getContent()));
                oneStepForAllPrg(prgList);
            } catch (Exception e) {
            }

        //remove the completed programs
        prgList = removeCompletedPrg((List<PrgState>) myRepository.getPrgList().getList());
    }
    else{
        executor.shutdownNow();
        List<PrgState> tmpList= (List<PrgState>)myRepository.getPrgList().getList();
        closeFillesGarbageCollector(tmpList.get(0));

        Queue<PrgState> newqueue= new LinkedList<PrgState>(prgList);
        MyList<PrgState> newprgList=new MyList<PrgState>();
        newprgList.setList(newqueue);

        myRepository.setPrgList(newprgList);
    }
    }

    public void allStep()
    {
        executor = Executors.newFixedThreadPool(2);

        //System.out.println(myRepository.getPrgList().getList().size());
        List<PrgState>  prgList=removeCompletedPrg((List<PrgState>)myRepository.getPrgList().getList());
        //System.out.println("in allstep\n");
        //System.out.println(prgList.size());
        while(prgList.size() > 0){
            try {
                for(int i=0;i<prgList.size();i++)
                    prgList.get(i).getHeap().setContent(conservativeGarbageCollector(
                            prgList.get(i).getSymTable().getContent().values(),
                            prgList.get(i).getHeap().getContent()));
                oneStepForAllPrg(prgList);
            }catch (Exception e)
            {}
            //remove the completed programs
            prgList=removeCompletedPrg((List<PrgState>)myRepository.getPrgList().getList());
        }
        executor.shutdownNow();

        List<PrgState> tmpList= (List<PrgState>)myRepository.getPrgList().getList();
        closeFillesGarbageCollector(tmpList.get(0));

        Queue<PrgState> newqueue= new LinkedList<PrgState>(prgList);
        MyList<PrgState> newprgList=new MyList<PrgState>();
        newprgList.setList(newqueue);

        myRepository.setPrgList(newprgList);
    }

//    public void allStep()
//    {
//        PrgState prg=myRepository.getCrtPrg();
//        try{
//            while(true){
//                System.out.println(prg.toString());
//                myRepository.addPrg(prg);
//                myRepository.logPrgStateExec(prg);
//                prg.oneStep();
//                prg.getHeap().setContent(conservativeGarbageCollector(
//                        prg.getSymTable().getContent().values(),
//                        prg.getHeap().getContent()));
//            }
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//            System.exit(0);
//        }
//        closeFillesGarbageCollector(prg);
//    }
}
