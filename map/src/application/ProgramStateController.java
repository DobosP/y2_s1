package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import Model.DataStructure.*;
import Model.PrgState;
import Model.Statement.IStatement;
import Repository.IRepo;
import Repository.InMemRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import javax.swing.text.html.ListView;

public class ProgramStateController implements Initializable {

    private IStatement statement;

    IRepo repo;
    ExecutorService executor;
    int crrtProgramStateId;

    @FXML
    private javafx.scene.control.ListView<String> exeStackView;

    @FXML
    private javafx.scene.control.TableView<SymTableEntry> symTableView;

    @FXML
    private javafx.scene.control.ListView<String> outView;

    @FXML
    private javafx.scene.control.TableView<FileTableEntry> fileTableView;

    @FXML
    private javafx.scene.control.TableView<HeapTableEntry> heapTableView;

    @FXML
    private TextArea prgStateNumber;

    @FXML
    private javafx.scene.control.ListView<String> programStatesIds;

    @FXML
    private Button runOneStepButton;

    public ProgramStateController() {

    }

    public void initSymTableView() {
        TableColumn firstCol = symTableView.getColumns().get(0);
        firstCol.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("name"));
        TableColumn secondCol = symTableView.getColumns().get(1);
        secondCol.setCellValueFactory(new PropertyValueFactory<SymTableEntry, Integer>("value"));
    }

    public void initHeapTableView() {
        TableColumn firstCol = heapTableView.getColumns().get(0);
        firstCol.setCellValueFactory(new PropertyValueFactory<HeapTableEntry, Integer>("address"));
        TableColumn secondCol = heapTableView.getColumns().get(1);
        secondCol.setCellValueFactory(new PropertyValueFactory<HeapTableEntry, Integer>("value"));
    }

    public void initFileTableView() {
        TableColumn firstCol = fileTableView.getColumns().get(0);
        firstCol.setCellValueFactory(new PropertyValueFactory<FileTableEntry, Integer>("id"));
        TableColumn secondCol = fileTableView.getColumns().get(1);
        secondCol.setCellValueFactory(new PropertyValueFactory<FileTableEntry, String>("filename"));
    }

    public void setStatement(final IStatement statement) {
        this.statement = statement;

        MyIStack<IStatement> exeStack = new MyStack<IStatement>();
        exeStack.push(statement);
        MyIDictionary<String, Integer> symTable = new MyDictionary<String, Integer>();
        MyIList<Integer> out = new MyList<Integer>();
        MyIDictionary<Integer, FileTableValue> fileTable = new MyFileTable<Integer, FileTableValue>();
        MyIDictionary<Integer, Integer> heapTable = new MyHeapTable<Integer, Integer>();
        PrgState state = new PrgState(exeStack, symTable, out, fileTable, heapTable, 1);

        List<PrgState> programList = new ArrayList<PrgState>();
        programList.add(state);

        this.repo = new InMemRepo(programList, "src/log_file");
        this.crrtProgramStateId = state.getId();

        this.initSymTableView();
        this.initHeapTableView();
        this.initFileTableView();

        programStatesIds.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {

                int prgStateId = Integer.parseInt(ProgramStateController.this.programStatesIds.getSelectionModel().getSelectedItem());
                ProgramStateController.this.crrtProgramStateId = prgStateId;
                ProgramStateController.this.updateViews();
            }
        });

        runOneStepButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                ProgramStateController.this.oneStepForAllPrg(ProgramStateController.this.repo.getPrgList());
                ProgramStateController.this.updateViews();
            }
        });

        executor = Executors.newFixedThreadPool(2);

        this.updateViews();
    }

    public void updateViews() {
        if (this.repo.getProgramState(this.crrtProgramStateId) == null) {
            this.crrtProgramStateId = this.repo.getPrgList().get(0).getId();
        }
        PrgState state = this.repo.getProgramState(this.crrtProgramStateId);

        ObservableList<String> exeStackItems = FXCollections.observableArrayList(state.getExeStack().toString());
        exeStackView.setItems(exeStackItems);

        ObservableList<SymTableEntry> symTableArr = FXCollections.observableArrayList();
        state.getSymTable().keySet().forEach(key -> {
            symTableArr.add(new SymTableEntry(key, state.getSymTable().get(key)));
        });
        if (symTableArr.size() > 0) {
            symTableView.setItems(symTableArr);
        }

        ArrayList<String> outArray = new ArrayList<>();
        for (Integer E : state.getOut().getList()) {
            outArray.add(E.toString());
        }
        ObservableList<String> outItems = FXCollections.observableArrayList(outArray);
        outView.setItems(outItems);

        ObservableList<FileTableEntry> fileTableArr = FXCollections.observableArrayList();
        state.getFileTable().keySet().forEach(key -> {
            fileTableArr.add(new FileTableEntry(key, state.getFileTable().get(key).getFilename()));
        });
        fileTableView.setItems(fileTableArr);


        ObservableList<HeapTableEntry> heapTableArr = FXCollections.observableArrayList();
        state.getHeapTable().keySet().forEach(key -> {
            heapTableArr.add(new HeapTableEntry(key, state.getHeapTable().get(key)));
        });
        heapTableView.setItems(heapTableArr);

        this.prgStateNumber.setText("Program states: " + this.repo.getPrgList().size());

        ObservableList<String> programStatesIdsArr = FXCollections.observableArrayList();
        repo.getPrgList().forEach(st -> {
            programStatesIdsArr.add(String.valueOf(st.getId()));
        });
        programStatesIds.setItems(programStatesIdsArr);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void oneStepForAllPrg(List<PrgState> stateList) {
        stateList.forEach(state -> {state.getHeapTable().setContent(this.conservativeGarbageCollector(
                state.getSymTable().getContent().values(),
                state.getHeapTable().getContent()
        ));});
        stateList = removeCompletedPrg(stateList);
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