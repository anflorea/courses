package Interpretor;

import Model.*;
import Repository.IRepo;
import Repository.Repo;
import Utils.*;
import Controller.*;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.table.*;

/**
 * Created by flo on 2016-11-16.
 */
public class Interpretor extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        IFileTable ft = new FileTable<String, FileData>();
        IStmt ex1 = new CompStmt(
                new AssignStmt("v", new ConstExp(2)),
                new PrintStmt(new VarExp("v")));
        PrgState prg1 = new PrgState(new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(), ex1, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>()));
        IRepo repo1 = new Repo(prg1,"log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(
                new AssignStmt("a",
                        new ArithExp('+',
                                new ConstExp(2),
                                new	ArithExp('*',
                                        new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b",new ArithExp('+',
                        new VarExp("a"),
                        new ConstExp(1))),
                        new PrintStmt(new VarExp("b"))));
        PrgState prg2 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex2,ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>()));
        IRepo repo2 = new Repo(prg2,"log2.txt");
        Controller ctr2 = new Controller(repo2);
        IStmt ex3 = new CompStmt(
                new AssignStmt("a", new ArithExp('-',
                        new ConstExp(2), new ConstExp(2))),
                new CompStmt(
                        new IfStmt(new VarExp("a"),
                                new AssignStmt("v",new ConstExp(2)),
                                new AssignStmt("v", new ConstExp(3))),
                        new PrintStmt(new VarExp("v"))));
        PrgState prg3 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex3, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>()));
        IRepo repo3 = new Repo(prg3,"log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStmt(
                new OpenRFile("var_f", "test.in"),
                new CompStmt(
                        new ReadFile("var_f", "var_c"),
                        new CompStmt(
                                new PrintStmt(new VarExp("var_c")),
                                new CompStmt(
                                        new IfStmt(new VarExp("var_c"),
                                                new PrintStmt(
                                                        new VarExp("var_c")),
                                                new PrintStmt(new ConstExp(0))),
                                        new CloseRFile("var_f")))));

        PrgState prg4 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),ex4,
                new FileTable<Integer, FileData>(),
                new MyHeap<Integer>(new HashMap<Integer, Integer>()));


        IRepo repo4 = new Repo(prg4, "log4.txt");

        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(
                new OpenRFile("var_f", "test.in"),
                new CompStmt(
                        new ReadFile("var_f", "c"),
                        new CompStmt(
                                new AssignStmt(new VarExp("d").toString(),
                                        new ArithExp('*',
                                                new VarExp("c"),
                                                new ConstExp(5))),
                                new CompStmt(
                                        new IfStmt(new VarExp("c"),
                                                new PrintStmt(
                                                        new VarExp("c")),
                                                new PrintStmt(new VarExp("d"))),
                                        new CloseRFile("var_f")))));

        PrgState prg5 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex5, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>()));


        IRepo repo5 = new Repo(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        IStmt ex6 = new CompStmt(
                new AssignStmt("v", new ConstExp(6)),
                new CompStmt(
                        new WhileStmt(new CompExp("!=", new ArithExp('-', new VarExp("v"), new ConstExp(4)), new ConstExp(0)),
                                new CompStmt(
                                        new PrintStmt(new VarExp("v")),
                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ConstExp(1)))
                                )),
                        new PrintStmt(new VarExp("v"))
                )
        );
        PrgState prg6 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex6, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>())
        );
        IRepo repo6 = new Repo(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(
                new AssignStmt("v", new ConstExp(10)),
                new CompStmt(
                        new NewStmt("x", new ConstExp(20)),
                        new CompStmt(
                                new NewStmt("a", new ConstExp(22)),
                                new CompStmt(
                                        new WriteHeapStmt("a", new ConstExp(30)),
                                        new CompStmt(
                                                new PrintStmt(new VarExp("a")),
                                                new CompStmt(
                                                        new PrintStmt(new ReadHeapExp("a")),
                                                        new AssignStmt("x", new ConstExp(50))
                                                )
                                        )
                                )
                        )
                )
        );
        PrgState prg7 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex7, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>())
        );
        IRepo repo7 = new Repo(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        IStmt ex8 = new CompStmt(new CompStmt(
                new CompStmt(
                        new AssignStmt("v", new ConstExp(10)),
                        new NewStmt("a", new ConstExp(22))),
                new CompStmt(new ForkStmt
                        (new CompStmt
                                (new CompStmt
                                        (new WriteHeapStmt("a", new ConstExp(30)), new AssignStmt("v", new ConstExp(32))),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))
        ), new ForkStmt(new CompStmt(new AssignStmt("v", new ConstExp(15)), new PrintStmt(new VarExp("a")))));
        PrgState prg8 = new PrgState(
                new ExeStack<IStmt>(),
                new SymTable<String, Integer>(),
                new Out<Integer>(),
                ex8, ft,
                new MyHeap<Integer>(new HashMap<Integer, Integer>())
        );
        IRepo repo8 = new Repo(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

//        TextMenu menu = new TextMenu();
//
//        menu.addCommand(new ExitCmd("0", "exit"));
//        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
//        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
//        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
//        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
//        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
//        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
//        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
//        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
//        menu.show();

        primaryStage.setTitle("Toy Language Interpretor");

        ListView<Controller> list = new ListView<Controller>();
        ObservableList<Controller> controllersData = FXCollections.observableArrayList(
                ctr1, ctr2, ctr3, ctr4,
                ctr5, ctr6, ctr7, ctr8
        );
        list.setItems(controllersData);
        list.setPrefSize(500, 700);


        Button btn = new Button();
        btn.setText("Start");



        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setVgap(15);

        Label errorLabel = new Label();
        Label titleLabel = new Label();
        titleLabel.setText("Please select an option and then press start:");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Button pressed -> " + list.getSelectionModel().getSelectedIndex());


                if (list.getSelectionModel().getSelectedIndex() == -1) {
                    errorLabel.setText("You must select an option first!");
                }
                else {
                    Controller ctrl = list.getSelectionModel().getSelectedItem();

                    errorLabel.setText("");

                    GridPane secondGrid = new GridPane();

                    Stage secondStage = new Stage();
                    secondStage.setScene(new Scene(secondGrid, 1200, 800));
                    secondStage.setTitle(String.valueOf(list.getSelectionModel().getSelectedIndex()));
                    secondGrid.setVgap(10);
                    secondGrid.setHgap(10);
                    secondGrid.setPadding(new Insets(10,10, 10, 10));

                    // ================= Program States List View =============================

                    ListView<PrgState> programStateList = new ListView<PrgState>();
                    ObservableList<PrgState> programData = FXCollections.observableList(ctrl.getRepo().getPrgList());


                    programStateList.setItems(programData);
                    programStateList.setMinHeight(780);
                    programStateList.setMaxHeight(780);
                    programStateList.setMaxWidth(180);
                    programStateList.setMinWidth(180);



                    secondGrid.add(programStateList, 0, 0);

                    // ==========================================================================

                    GridPane middleGrid = new GridPane();

                    middleGrid.setMaxWidth(480);
                    middleGrid.setMinWidth(480);
                    middleGrid.setMaxHeight(780);
                    middleGrid.setMinHeight(780);

                    middleGrid.setVgap(10);

                    secondGrid.add(middleGrid, 1, 0);

                    GridPane rightGrid = new GridPane();

                    rightGrid.setMaxWidth(480);
                    rightGrid.setMinWidth(480);
                    rightGrid.setMaxHeight(780);
                    rightGrid.setMinHeight(780);

                    rightGrid.setVgap(10);

                    secondGrid.add(rightGrid, 2, 0);

                    // ================== # Prg States TextField ================================

                    TextField prgStates = new TextField();

                    prgStates.setText(String.valueOf(ctrl.getRepo().getPrgList().size()));
                    prgStates.editableProperty().setValue(false);

                    middleGrid.add(prgStates, 0, 0);

                    // ==========================================================================


                    // ================== Heap Table Table View =================================

                    TableView<TableDelegate> heapTable = new TableView<>();

                    TableColumn heapKey = new TableColumn("Key");
                    heapKey.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("key")
                    );

                    TableColumn heapValue = new TableColumn("Value");
                    heapValue.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("value")
                    );

                    heapKey.setPrefWidth(240);
                    heapValue.setPrefWidth(240);

                    heapTable.getColumns().addAll(heapKey, heapValue);

                    middleGrid.add(heapTable, 0, 2);

                    // ==========================================================================


                    // ================== Out List View =========================================

                    ListView<Integer> outListView = new ListView<Integer>();

                    ObservableList<Integer> outListData = FXCollections.observableList(ctrl.getRepo().getPrgList().get(0).getOutput().toList());

                    outListView.setItems(outListData);

                    outListView.setPrefWidth(480);

                    middleGrid.add(outListView, 0, 1);

                    // ==========================================================================

                    // ================== File Table Table View =================================

                    TableView<TableDelegate> fileTableView = new TableView<>();

                    TableColumn fileKey = new TableColumn("Key");
                    fileKey.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("key")
                    );

                    TableColumn fileValue = new TableColumn("Value");
                    fileValue.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("value")
                    );
                    fileKey.setPrefWidth(240);
                    fileValue.setPrefWidth(240);

                    fileTableView.getColumns().addAll(fileKey, fileValue);

                    middleGrid.add(fileTableView, 0, 3);

                    // ==========================================================================

                    // ================== Symbol Table Table View ===============================

                    TableView<TableDelegate> symbolTableView = new TableView<>();

                    TableColumn symbolKey = new TableColumn("Key");
                    symbolKey.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("key")
                    );
                    TableColumn symbolValue = new TableColumn("Value");
                    symbolValue.setCellValueFactory(
                            new PropertyValueFactory<TableDelegate, String>("value")
                    );
                    symbolKey.setPrefWidth(240);
                    symbolValue.setPrefWidth(240);

                    symbolTableView.getColumns().addAll(symbolKey, symbolValue);

                    rightGrid.add(symbolTableView, 0, 0);

                    // ==========================================================================

                    // ================== ExeStack List View ====================================

                    ListView<IStmt> exeStackListView = new ListView<>();

                    int index = programStateList.getSelectionModel().getSelectedIndex();
                    if (index >= 0) {
                        ObservableList<IStmt> exeStackData = FXCollections.observableList(ctrl.getRepo().getPrgList().get(index).getStack().toList());
                        exeStackListView.setItems(exeStackData);
                    }


                    exeStackListView.setPrefWidth(480);

                    rightGrid.add(exeStackListView, 0, 1);

                    // ==========================================================================

                    // ================== Run One Step Button ===================================

                    Button runOneStepButton = new Button();

                    runOneStepButton.setText("Run One Step");

                    runOneStepButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("One Step Button pressed.");

                            try {
                                ctrl.allStepGUI();

                                prgStates.setText(String.valueOf(ctrl.getRepo().getPrgList().size()));

                                ObservableList<Integer> newOutListData = FXCollections.observableList(ctrl.getRepo().getPrgList().get(0).getOutput().toList());
                                outListView.setItems(newOutListData);

                                int index = programStateList.getSelectionModel().getSelectedIndex();
                                if (index >= 0) {
                                    ObservableList<IStmt> exeStackData = FXCollections.observableList(ctrl.getRepo().getPrgList().get(index).getStack().toList());
                                    exeStackListView.setItems(exeStackData);

                                    final ObservableList<TableDelegate> symTableData = FXCollections.observableList(new ArrayList<TableDelegate>());
                                    for (String key: ctrl.getRepo().getPrgList().get(index).getSymTable().keySet()) {
                                        symTableData.add(new TableDelegate(key, String.valueOf(ctrl.getRepo().getPrgList().get(index).getSymTable().getValue(key))));
                                    }
                                    symbolTableView.setItems(symTableData);
                                }

                                ObservableList<PrgState> programData = FXCollections.observableList(ctrl.getRepo().getPrgList());

                                programStateList.setItems(programData);

                                final ObservableList<TableDelegate> heapTableData = FXCollections.observableList(new ArrayList<TableDelegate>());
                                for (Integer key: ctrl.getRepo().getPrgList().get(0).getHeap().keySet()) {
                                    heapTableData.add(new TableDelegate(String.valueOf(key), String.valueOf(ctrl.getRepo().getPrgList().get(0).getHeap().getMap().get(key))));
                                }
                                heapTable.setItems(heapTableData);

                                final ObservableList<TableDelegate> fileTableData = FXCollections.observableList(new ArrayList<TableDelegate>());
                                for (Integer key: ctrl.getRepo().getPrgList().get(0).getFileTable().keySet()) {
                                    fileTableData.add(new TableDelegate(String.valueOf(key), String.valueOf(ctrl.getRepo().getPrgList().get(0).getFileTable().getValue(key))));
                                }
                                fileTableView.setItems(fileTableData);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (CustomException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    programStateList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PrgState>() {
                        @Override
                        public void changed(ObservableValue<? extends PrgState> observable, PrgState oldValue, PrgState newValue) {
                            System.out.println("Selected item: " + programStateList.getSelectionModel().getSelectedIndex());

                            int index = programStateList.getSelectionModel().getSelectedIndex();
                            if (index >= 0) {
                                ObservableList<IStmt> exeStackData = FXCollections.observableList(ctrl.getRepo().getPrgList().get(index).getStack().toList());
                                exeStackListView.setItems(exeStackData);

                                final ObservableList<TableDelegate> symTableData = FXCollections.observableList(new ArrayList<TableDelegate>());
                                for (String key: ctrl.getRepo().getPrgList().get(index).getSymTable().keySet()) {
                                    try {
                                        symTableData.add(new TableDelegate(key, String.valueOf(ctrl.getRepo().getPrgList().get(index).getSymTable().getValue(key))));
                                    } catch (CustomException e) {
                                        e.printStackTrace();
                                    }
                                }
                                symbolTableView.setItems(symTableData);
                            }
                        }
                    });

                    middleGrid.add(runOneStepButton, 0, 4);

                    // ==========================================================================
                    secondStage.show();
                }
            }
        });

        grid.add(titleLabel, 0, 0);
        grid.add(list, 0, 1);
        grid.add(errorLabel, 0, 2);
        grid.add(btn, 0, 3);


        primaryStage.setScene(new Scene(grid, 800, 600));
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Platform.exit());

    }
}



