package controller;
import model.IntStatement;
import repository.IntRepository;
import repository.Repository;
import model.*;
import DataStructure.*;
import ExceptionHandling.MyException;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    private IntRepository rep;
    private ExecutorService executor;
    public Controller(IntRepository _rep){
        rep = _rep;
    }


    public void addPrgState(ProgState state){
        rep.addPrgState(state);
    }

    List<ProgState> removeCompletedPrg(List<ProgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public ProgState OneStep(int index) throws MyException {

        return null;

    }
    void oneStepForAllPrg(List<ProgState> prgList) throws InterruptedException {

        prgList.forEach(prg -> {
            try {
                rep.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgState>> callList = prgList.stream()
                .map((ProgState p) -> (Callable<ProgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());


        List<ProgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                catch(Exception e) {
                    e.printStackTrace();
                    return null;
                }
                }).filter(p -> p!=null)
                            .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                rep.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        rep.setPrgList(prgList);

    }

    public void AllSteps() throws MyException, InterruptedException {


        executor = Executors.newFixedThreadPool(2);
        List<ProgState> prgList=removeCompletedPrg(rep.getPrgList());
        while(prgList.size() > 0){
                prgList.get(0).getHeap().setContent(
                    conservativeGarbageCollector(
                            prgList.get(0).getSymTable().getContent().values(),
                            prgList.get(0).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            prgList=removeCompletedPrg(rep.getPrgList());
        }
        executor.shutdownNow();
        List<ProgState> tmpList= rep.getPrgList();

        tmpList.get(0).getFileTable().getContent().forEach((k, v) -> {
            try {
                v.getsecond().close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        });

        rep.setPrgList(prgList);

    }


     Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap){
        return heap.entrySet().stream().
                filter(e->symTableValues.contains(e.getKey())).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}

    public void displayProgState(ProgState state){
        System.out.println(state.toString());
    }

}
