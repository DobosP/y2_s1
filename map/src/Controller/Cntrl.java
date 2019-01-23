package Controller;

import Model.DataStructure.FileTableValue;
import Model.DataStructure.MyIDictionary;
import Model.PrgState;
import Repository.IRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Cntrl {
    IRepo repo;
    ExecutorService executor;

    public Cntrl(final IRepo repo) {
        this.repo = repo;
    }


    public void oneStepForAllPrg(List<PrgState> stateList) {
        stateList.forEach(state -> repo.logPrgStateExec(state));
        List<Callable<PrgState>> callList = stateList.stream().map((PrgState state) -> (Callable<PrgState>)(() -> { return state.oneStep(); })).collect(Collectors.toList());
        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> { try { return future.get();}
                            catch(Exception e) {
                                System.out.println(e.getMessage());
                                System.exit(1);
                                return null;
                            }}).filter(p -> p!=null)
                                    .collect(Collectors.toList());
            //add the new created threads to the list of existing threads
            stateList.addAll(newPrgList);
            //------------------------------------------------------------------------------

            //after the execution, print the PrgState List into the log file
            stateList.forEach(prg ->repo.logPrgStateExec(prg));
            //Save the current programs
            repo.setPrgList(stateList);
        } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.exit(1);
        }
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            prgList.forEach(state -> {state.getHeapTable().setContent(this.conservativeGarbageCollector(
                    state.getSymTable().getContent().values(),
                    state.getHeapTable().getContent()
            ));});

            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList = removeCompletedPrg(repo.getPrgList());

        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        List<PrgState> stateList = repo.getPrgList();
        stateList.forEach(state -> {
            MyIDictionary<Integer, FileTableValue> fileTable = state.getFileTable();
            state.getSymTable().keySet().forEach(key -> {
                if (fileTable.containsKey(state.getSymTable().get(key))) {
                    FileTableValue tableValue = fileTable.get(state.getSymTable().get(key));
                    BufferedReader br = tableValue.getFileDescriptor();
                    try {
                        br.close();
                        System.out.println("Closed file " + tableValue.getFilename() + "\n");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        });

        // update the repository state
        repo.setPrgList(prgList);
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                               Map<Integer, Integer> heapTable) {
        return heapTable.entrySet()
                        .stream()
                        .filter(e->symTableValues.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<PrgState> removeCompletedPrg(List<PrgState> stateList) {
        return stateList.stream().filter(state -> state.isNotCompleted()).collect(Collectors.toList());
    }
}