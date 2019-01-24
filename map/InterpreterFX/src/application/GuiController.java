package application;

import DataStructure.*;
import ExceptionHandling.MyException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.*;
import repository.IntRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class GuiController implements Initializable {

    private IntRepository rep;
    private ExecutorService executor;
    Integer program_state_id;


    public Button one_step_button;
    public ListView prog_state_table;
    public TextField program_id;
    public ListView out_table;
    public ListView exec_stack_table;
    public TableView<Pair<String,Integer>> sym_table;
    public TableView<Pair<Integer,String>> file_table;
    public TableView<Pair<Integer,Integer>> heap_table;
    private IntStatement statement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.rep = new Repository("lgoFile.txt");
        executor = Executors.newFixedThreadPool(2);
        initFileTable();
        initHeapTable();
        initSynTable();
    }


    void UpdateView(){
        List<ProgState> prog_states = rep.getPrgList();

        if(prog_states.size() == 0)
            return;

        ProgState state = rep.getProgState(program_state_id);

        if(state == null)
        {
            state = prog_states.get(0);
            this.program_state_id = state.getId();
        }

        program_id.setText( Integer.toString(rep.getPrgList().size()));

        ObservableList<String> execstack = FXCollections.observableArrayList(state.getExecStack().toString());
        ObservableList<String> out = FXCollections.observableArrayList(state.getOut().toString());

        ObservableList<String> ids_list = FXCollections.observableArrayList();

        for(ProgState curr_state: prog_states)
            ids_list.add( Integer.toString( + curr_state.getId()));


        ObservableList<Pair<String,Integer>> symtable = FXCollections.observableArrayList();

        Map<String, Integer> sym_map = state.getSymTable().top().getContent();
        sym_map.keySet().forEach(key ->
        {
            symtable.add(new Pair<String, Integer>(key, sym_map.get(key)));
        });

        ObservableList<Pair<Integer,Integer>> heaptable = FXCollections.observableArrayList();

        Map<Integer, Integer> heap_map = state.getHeap().getContent();
        heap_map.keySet().forEach(key ->
        {
            heaptable.add(new Pair<Integer, Integer>(key, heap_map.get(key)));
        });

        ObservableList<Pair<Integer,String>> filetable = FXCollections.observableArrayList();

        Map<Integer, Pair<String, BufferedReader>> file_map = state.getFileTable().getContent();
        file_map.keySet().forEach(key ->
        {
            filetable.add(new Pair<Integer, String>(key, file_map.get(key).getfirst()));
        });


        file_table.setItems(filetable);
        heap_table.setItems(heaptable);
        sym_table.setItems(symtable);
        exec_stack_table.setItems(execstack);
        out_table.setItems(out);
        prog_state_table.setItems(ids_list);
    }


    public void initSynTable(){
        TableColumn firstcolum = sym_table.getColumns().get(0);
        firstcolum.setCellValueFactory(new PropertyValueFactory<Pair<String,Integer>, String>("first"));
        TableColumn secondcolum = sym_table.getColumns().get(1);
        secondcolum.setCellValueFactory(new PropertyValueFactory<Pair<String,Integer>, Integer>("second"));

    }

    public void initFileTable(){
        TableColumn firstcolum = file_table.getColumns().get(0);
        firstcolum.setCellValueFactory(new PropertyValueFactory<Pair<Integer,String>, Integer>("first"));
        TableColumn secondcolum = file_table.getColumns().get(1);
        secondcolum.setCellValueFactory(new PropertyValueFactory<Pair<Integer,String>, String>("second"));

    }
    public void initHeapTable(){
        TableColumn firstcolum = heap_table.getColumns().get(0);
        firstcolum.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Integer>, Integer>("first"));
        TableColumn secondcolum = heap_table.getColumns().get(1);
        secondcolum.setCellValueFactory(new PropertyValueFactory<Pair<Integer,Integer>, Integer>("second"));

    }


    public void SetStatemant(final IntStatement _statement){
        statement = _statement;
        rep.addPrgState(createProgState(statement));
        program_state_id = 1;
        UpdateView();
    }

    public  ProgState createProgState(IntStatement state){

        MyIntStack<IntStatement> stack = new MyStack<IntStatement>();
        MyIntStack< MyIntDict<String, Integer>> dict = new MyStack<>();
        dict.push(new MyDict<>());
        MyIntList<Integer> list = new MyList<Integer>();
        IntFileTable filetable = new FileTable();
        IntHeap heap = new Heap();
        return new ProgState(stack, dict, list, filetable, heap, state);

    }



    List<ProgState> removeCompletedPrg(List<ProgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }



    public void run_one_step(ActionEvent actionEvent) {
        try {
            oneStepForAllPrg();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.setTitle("Error message!");
            alert.showAndWait();
        }
        UpdateView();
    }



    void oneStepForAllPrg() throws InterruptedException, MyException {
        List<ProgState> prgList=removeCompletedPrg(rep.getPrgList());
        if(prgList.size() == 0)
            throw new MyException("Execution is finished!");


        prgList.get(0).getHeap().setContent(
                conservativeGarbageCollector(
                        prgList.get(0).getSymTable().top().getContent().values(),
                        prgList.get(0).getHeap().getContent()));

        prgList.forEach(prg -> {
            try {
                rep.logPrgStateExec(prg);
            } catch (MyException e) {
                e.printStackTrace();
                System.exit(0);
            }
        });

        List<Callable<ProgState>> callList = prgList.stream()
                .map((ProgState p) -> (Callable<ProgState>)(() -> {try {return p.oneStep();}
                catch (MyException e){
                    e.printStackTrace();
                    System.exit(0);
                    return  null;
                }}))
                .collect(Collectors.toList());


        List<ProgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try { return future.get();}
                catch(Exception e) {

                    e.printStackTrace();
                    System.exit(0);
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
                System.exit(0);
            }
        });

        rep.setPrgList(prgList);

    }


    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap){
        return heap.entrySet().stream().
                filter(e->symTableValues.contains(e.getKey())).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}


    public void selected_state(MouseEvent mouseEvent) {

        String selecte_id = prog_state_table.getSelectionModel().getSelectedItem().toString();
        program_state_id = Integer.parseInt(selecte_id);
        UpdateView();
    }
}
