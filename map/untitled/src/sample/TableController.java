package sample;

import controller.Controller;
import domain.PrgState;
import domain.adt.*;
import domain.expressions.ConstExp;
import domain.expressions.HeapReadExp;
import domain.expressions.VarExp;
import domain.statements.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import repository.Repository;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.util.*;

public class TableController {

    private int check=0;
    private Controller ctrl;
    private IStmt statement;

    @FXML
    private TableView<FX2arg<String,Integer>> STtableView;
    @FXML
    private TableColumn<FX2arg<String,Integer>,String> STName;
    @FXML
    private TableColumn<FX2arg<String,Integer>,Integer> STValue;
    @FXML
    private TableView<FX2arg<Integer,String>> FTtableView;
    @FXML
    private TableColumn<FX2arg<Integer,String>,Integer> FTName;
    @FXML
    private TableColumn<FX2arg<Integer,String>,String> FTValue;
    @FXML
    private TableView<FX2arg<Integer,Integer>> HtableView;
    @FXML
    private TableColumn<FX2arg<Integer,Integer>,Integer> HName;
    @FXML
    private TableColumn<FX2arg<Integer,Integer>,Integer> HValue;
    @FXML
    private TableView<FX1arg<Integer>> OtableView;
    @FXML
    private TableColumn<FX1arg<Integer>,Integer> OName;
    @FXML
    private TableView<FX1arg<Integer>> PStableView;
    @FXML
    private TableColumn<FX1arg<Integer>,Integer> PSName;
    @FXML
    private TableView<FX1arg<String>> EStableView;
    @FXML
    private TableColumn<FX1arg<String>,String> ESName;
    @FXML
    private Button NWinBT;
    @FXML
    private TextField TNWin;
    @FXML
    private TextField PrgNr;


    @FXML
    public void newWin()
    {
        GridPane root = new GridPane();
        Stage secondStage=new Stage();
        Scene scene = new Scene(root,200,200);
        // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        TableView<FX1arg<String>> list = new TableView<FX1arg<String>>();
        list.setMinSize(800,500);
        list.setMaxSize(800,500);

        IStmt ex4=new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAlocStmt("v",new ConstExp(20)),new CompStmt(new HeapAlocStmt("a",new ConstExp(22)),new CompStmt(new HeapWriteStmt("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new CompStmt(new AssignStmt("a",new ConstExp(0)),new AssignStmt("a",new ConstExp(100))))))));
        IStmt ex2=new CompStmt(new OpenRFile("t1","test.txt"),new CompStmt(new ReadFile(new VarExp("t1"),"ceva"),new ReadFile(new VarExp("t1"),"altceva")));
        IStmt ex7= new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(10)),new HeapAlocStmt("a",new ConstExp(22))),new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a",new ConstExp(30)),new CompStmt(new AssignStmt("v",new ConstExp(32)),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp("a")))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp("a")))));
        //System.out.println(ex7.toString());

        TableColumn Col = new TableColumn("Programs");
        list.getColumns().add(Col);

        FX1arg<String> elem=new FX1arg<String>(ex7.toString());
        FX1arg<String> elem1=new FX1arg<String>(ex4.toString());
        FX1arg<String> elem2=new FX1arg<String>(ex2.toString());
        ObservableList<FX1arg<String>> istms=FXCollections.observableArrayList(elem,elem1,elem2);
        Col.setCellValueFactory(new PropertyValueFactory<FX1arg<String>,String>("name"));
        list.setItems(istms);
        root.add(list,1,1);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FX1arg<String>>() {

            @Override
            public void changed(ObservableValue<? extends FX1arg<String>> observable, FX1arg<String> oldValue, FX1arg<String> newValue) {
                if(newValue.getName().equals(ex4.toString()))
                    statement=ex4;
                else
                    if(newValue.getName().equals(ex7.toString()))
                        statement=ex7;
                    else
                        if(newValue.getName().equals(ex2.toString()))
                            statement=ex2;
            }
        });
        secondStage.setScene(scene);
        secondStage.setTitle("FXML TableView Example");
        secondStage.show();
    }

    @FXML
    public void executa()
    {
        //IStmt ex7= new CompStmt(new CompStmt(new AssignStmt("v",new ConstExp(10)),new HeapAlocStmt("a",new ConstExp(22))),new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a",new ConstExp(30)),new CompStmt(new AssignStmt("v",new ConstExp(32)),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp("a")))))),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new HeapReadExp("a")))));
        //IStmt ex4=new CompStmt(new AssignStmt("v",new ConstExp(10)),new CompStmt(new HeapAlocStmt("v",new ConstExp(20)),new CompStmt(new HeapAlocStmt("a",new ConstExp(22)),new CompStmt(new HeapWriteStmt("a",new ConstExp(30)),new CompStmt(new PrintStmt(new VarExp("a")),new CompStmt(new AssignStmt("a",new ConstExp(0)),new AssignStmt("a",new ConstExp(100))))))));
        //MyDictionary<String,IStmt> execmap=new MyDictionary<String,IStmt>();
        //execmap.add(ex7.toString(),ex7);
        //execmap.add(ex4.toString(),ex4);

        if(check==0) {
            MyIStack<IStmt> exeStack6 = new MyStack<IStmt>();
            MyIDictionary<String, Integer> symTable6 = new MyDictionary<String, Integer>();
            MyIList<Integer> out6 = new MyList<Integer>();
            MyIDictionary<Integer, MyTuple<String, BufferedReader>> fileTable6 = new MyDictionary<Integer, MyTuple<String, BufferedReader>>();
            MyIHeap<Integer, Integer> heap6 = new MyHeap<Integer, Integer>();

            Repository repo7 = new Repository("log7.txt");
            controller.Controller ctr7 = new Controller(repo7);

            //System.out.println(TNWin.getText());
                    try {
                        System.out.println("Statement "+statement.toString());
                        PrgState prg7 = new PrgState(7, exeStack6, symTable6, out6, statement, fileTable6, heap6);
                        ctr7.addProgram(prg7);
                        ctr7.stepOnce();
                        check=1;
                        ctrl=ctr7;
                    } catch (Exception e) {
            }
            //System.out.println("aicicicicici");
            upd(ctr7);
        }
        else
        {
            try {
                ctrl.stepOnce();
                upd(ctrl);
            }catch (Exception e)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("EmptyStack");
                alert.setHeaderText("Stack is empty");
                alert.setContentText("Finish!!");

                alert.showAndWait();
            }
        }
    }

    private void upd(Controller c)
    {
        Queue<PrgState> ceva=c.getRepo().getPrgList().getList();
        System.out.println("Size:"+ceva.size());
        for (PrgState pr: ceva) {
            populate(pr);
        }
        //TNWin.setText(String.valueOf(ce));
    }

    private void populate(PrgState c)
    {
        ObservableList<FX2arg<String,Integer>> list = FXCollections.observableArrayList();
        HashMap<String,Integer> hm=c.getSymTable().getContent();
        for(String st:hm.keySet())
        {
            FX2arg<String,Integer> f=new FX2arg<String, Integer>(st,hm.get(st));
            //System.out.println(st+ " "+hm.get(st).toString());
            list.add(f);
        }
        if(list != null)
            STtableView.setItems(list);

        MyIList<Integer> ou=c.getOut();
        Queue<Integer> ou1=new LinkedList<Integer>();
        for(Integer elem:ou.getList())
            ou1.add(elem);
        ObservableList<FX1arg<Integer>> list1 = FXCollections.observableArrayList();
        System.out.println("OUT After While:"+ou.toString());
        Integer outElem=ou1.poll();
        while(outElem!=null)
        {
            //System.out.println("OUT After:"+ou.toString());
            FX1arg<Integer> e=new FX1arg<Integer>(outElem);
            outElem=ou1.poll();
            //System.out.println("OUT Before:"+ou.toString());
            list1.add(e);
        }

        if(list1 !=null)
            OtableView.setItems(list1);

        ObservableList<FX2arg<Integer,String>> list2 = FXCollections.observableArrayList();
        MyIDictionary<Integer,MyTuple<String, BufferedReader>> fl=c.getFileTable();
        for (Integer nr: fl.getKeys())
        {
            try{
                MyTuple<String,BufferedReader> e=fl.lookup(nr);
                FX2arg<Integer,String> f=new FX2arg<Integer, String>(nr,e.getFirst());
                list2.add(f);
            }catch (Exception e)
            {}
        }

        if(list2!=null)
            FTtableView.setItems(list2);

        ObservableList<FX2arg<Integer,Integer>> list3 = FXCollections.observableArrayList();
        HashMap<Integer,Integer> h=c.getHeap().getContent();
        for (Integer nr: h.keySet()) {
            FX2arg<Integer,Integer> f=new FX2arg<Integer, Integer>(nr,h.get(nr));
            list3.add(f);
        }

        if(list3!=null)
            HtableView.setItems(list3);

        ObservableList<FX1arg<Integer>> list4=FXCollections.observableArrayList();
        for(PrgState p:ctrl.getRepo().getPrgList().getList()) {
            FX1arg<Integer> f = new FX1arg<Integer>(p.getId());
            list4.add(f);
        }

        if(list4!=null)
            PStableView.setItems(list4);

        count();

    }

    @FXML
    public void count()
    {
        int nr=0;
        for(PrgState p:ctrl.getRepo().getPrgList().getList())
            nr++;
        PrgNr.setText(String.valueOf(nr));

    }

    @FXML
    public void initialize()
    {

        STName.setCellValueFactory(new PropertyValueFactory<FX2arg<String,Integer>,String>("name"));
        STValue.setCellValueFactory(new PropertyValueFactory<FX2arg<String,Integer>,Integer>("value"));
        FTName.setCellValueFactory(new PropertyValueFactory<FX2arg<Integer,String>,Integer>("name"));
        FTValue.setCellValueFactory(new PropertyValueFactory<FX2arg<Integer,String>,String>("value"));
        HName.setCellValueFactory(new PropertyValueFactory<FX2arg<Integer,Integer>,Integer>("name"));
        HValue.setCellValueFactory(new PropertyValueFactory<FX2arg<Integer,Integer>,Integer>("value"));
        OName.setCellValueFactory(new PropertyValueFactory<FX1arg<Integer>,Integer>("name"));
        PSName.setCellValueFactory(new PropertyValueFactory<FX1arg<Integer>,Integer>("name"));
        ESName.setCellValueFactory(new PropertyValueFactory<FX1arg<String>,String>("name"));
        STtableView.setItems(getSymTable());
        FTtableView.setItems(getFileTable());
        HtableView.setItems(getHeap());
        OtableView.setItems(getOut());
        PStableView.setItems(getProgState());


        //PStableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Select item at index = 1
        //PStableView.getSelectionModel().selectIndices(1);

        // Focus
        //PStableView.getFocusModel().focus(2);

        PStableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FX1arg<Integer>>() {

            @Override
            public void changed(ObservableValue<? extends FX1arg<Integer>> observable, FX1arg<Integer> oldValue, FX1arg<Integer> newValue) {
                    EStableView.setItems(getProgState1(newValue));
            }
        });


    }

    private ObservableList<FX1arg<Integer>> getOut()
    {
        FX1arg<Integer> st1=new FX1arg<Integer>(1);
        ObservableList<FX1arg<Integer>> list=FXCollections.observableArrayList(st1);
        return list;
    }

    private ObservableList<FX1arg<Integer>> getProgState()
    {
        FX1arg<Integer> st1=new FX1arg<Integer>(10);
        FX1arg<Integer> st2=new FX1arg<Integer>(11);
        FX1arg<Integer> st3=new FX1arg<Integer>(12);
        ObservableList<FX1arg<Integer>> list=FXCollections.observableArrayList(st1,st2,st3);
        return list;
    }

    private ObservableList<FX1arg<String>> getProgState1(FX1arg<Integer> newValue)
    {
        ObservableList<FX1arg<String>> list=FXCollections.observableArrayList();
        for (PrgState e:
        ctrl.getRepo().getPrgList().getList()) {
            if(e.getId()==newValue.getName())
                {
                FX1arg<String> f=new FX1arg<String>(e.getExeStack().toString());
                list.add(f);
            }}
        return list;
    }

    private ObservableList<FX2arg<Integer,Integer>> getHeap()
    {
        FX2arg<Integer,Integer> st1=new FX2arg<Integer,Integer>(1,1);
        ObservableList<FX2arg<Integer,Integer>> list=FXCollections.observableArrayList(st1);
        return list;
    }

    private ObservableList<FX2arg<String,Integer>> getSymTable()
    {
        FX2arg<String,Integer> st1=new FX2arg<String,Integer>("a",1);
        ObservableList<FX2arg<String,Integer>> list= FXCollections.observableArrayList(st1);
        return list;
    }

    private ObservableList<FX2arg<Integer,String>> getFileTable()
    {
        FX2arg<Integer,String> st1=new FX2arg<Integer,String>(1,"ft");
        ObservableList<FX2arg<Integer,String>> list=FXCollections.observableArrayList(st1);
        return list;
    }


}

