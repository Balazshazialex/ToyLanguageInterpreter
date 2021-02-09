package sample;

import Controller.Controller;
import Model.ADT.*;
import Model.MyException;
import Model.PrgState;
import Model.Stmt.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Runform implements Initializable {
    @FXML
    private TextField PrgStateCount;
    @FXML
    private TableView<Pair<Integer, Value>> TableViewHeap;
    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> ColumnHeapAdress;
    @FXML
    private TableColumn<Pair<Integer, Value>, Value> ColumnHeapValue;
    @FXML
    private ListView<Integer> PrgStateIdsContent;
    @FXML
    private TableView<Pair<String, Value>> SymTableView;

    @FXML
    private TableColumn<Pair<String, Value>, String> SymTableNames;

    @FXML
    private TableColumn<Pair<String, Value>, Value> SymTableValues;

    @FXML
    private TableView<TPair<Integer, Integer,ArrayList<Integer>>> SemaphoreTableView;

    @FXML
    private TableColumn<TPair<Integer, Integer,ArrayList<Integer>>, Integer> SemaphoreIndex;

    @FXML
    private TableColumn<TPair<Integer, Integer,ArrayList<Integer>>, Integer> SemaphoreValues;

    @FXML
    private TableColumn<TPair<Integer, Integer,ArrayList<Integer>>, ArrayList<Integer>> SemaphoreLists;


    @FXML
    private TableView<Pair<Integer, Integer>> LockTableView;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> LockIndex;

    @FXML
    private TableColumn<Pair<Integer, Integer>, Integer> LockValues;

    @FXML
    private ListView<Value> OutTableContent;
    @FXML
    private Button OneStepButton;
    @FXML
    private ListView<StringValue> FileTableContent;
    @FXML
    private ListView<String> ExeStackView;
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
        populateProgramStateIdentifiers();
    }
    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream()
                .filter(PrgState::isNotCompleted)
                .collect(Collectors.toList());
    }
    private List<Integer> getPrgStateId(List<PrgState> programStateList) {
        return programStateList.stream().map(PrgState::getId_thread).collect(Collectors.toList());
    }



    private void populateProgramStateIdentifiers() {

        List<PrgState> programStates = controller.getRepository().getPrgList();
        PrgStateIdsContent.setItems(FXCollections.observableList(getPrgStateId(programStates)));
        PrgStateCount.setText("" + programStates.size());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SymTableNames.setCellValueFactory(new PropertyValueFactory<>("first"));
        SymTableValues.setCellValueFactory(new PropertyValueFactory<>("second"));

        ColumnHeapAdress.setCellValueFactory(new PropertyValueFactory<>("first"));
        ColumnHeapValue.setCellValueFactory(new PropertyValueFactory<>("second"));

        SemaphoreIndex.setCellValueFactory(new PropertyValueFactory<>("first"));
        SemaphoreValues.setCellValueFactory(new PropertyValueFactory<>("second"));
        SemaphoreLists.setCellValueFactory(new PropertyValueFactory<>("third"));

        LockIndex.setCellValueFactory(new PropertyValueFactory<>("first"));
        LockValues.setCellValueFactory(new PropertyValueFactory<>("second"));

        PrgStateIdsContent.setOnMouseClicked(mouseEvent -> { changeProgramState(getPrgState()); });
        OneStepButton.setOnAction(actionEvent -> {
            try {
                executeOneStep();
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
    }

    private void executeOneStep() throws MyException {
        if(controller == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        List<PrgState> programStates = removeCompletedPrg(controller.getRepository().getPrgList());
        boolean programStateLeft = getPrgState().getExeStack().isEmpty();
        if(programStateLeft ){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute on this thread", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        controller.oneStepForAllPrg(programStates);
        changeProgramState(getPrgState());
        populateProgramStateIdentifiers();

    }

    private void changeProgramState(PrgState currentProgramState) {
        if (currentProgramState == null)
            return;
        populateExecutionStack(currentProgramState);
        PopulateSymbolTable(currentProgramState);
        PopulateOutput(currentProgramState);
        PopulateFileTable(currentProgramState);
        PopulateHeap(currentProgramState);
        PopulateSemaphoreTable(currentProgramState);
        PopulateLockTable(currentProgramState);
    }

    private void populateExecutionStack(PrgState currentProgramState) {
        MyIStack<IStmt> executionStack = currentProgramState.getExeStack();

        List<String> executionStackList = new ArrayList<>();
        for(IStmt s : executionStack.getAll()){
            executionStackList.add(s.toString());
        }

        ExeStackView.setItems(FXCollections.observableList(executionStackList));
        ExeStackView.refresh();
    }

    private void PopulateFileTable(PrgState currentProgram) {
        FileTableContent.setItems(FXCollections.observableArrayList(currentProgram.getFiletable().getListView()));
    }



    private void PopulateOutput(PrgState currentProgram) {
        OutTableContent.setItems(FXCollections.observableArrayList(currentProgram.getOut().getAll()));
        OutTableContent.refresh();
    }

    private void PopulateHeap(PrgState currentPrg) {
        IHeap<Integer, Value> symbolTable = currentPrg.getHeap();

        List<Pair<Integer, Value>> HeapTableList = new ArrayList<>();
        HeapTableList.addAll(symbolTable.getAll());
        TableViewHeap.setItems(FXCollections.observableList(HeapTableList));
        TableViewHeap.refresh();
    }

    private void PopulateSymbolTable(PrgState currentPrg) {
        MyIDict<String, Value> symbolTable = currentPrg.getSymTable();

        List<Pair<String, Value>> symbolTableList = new ArrayList<>();
        symbolTableList.addAll(symbolTable.getAll());
        SymTableView.setItems(FXCollections.observableList(symbolTableList));
        SymTableView.refresh();
    }

    private void PopulateSemaphoreTable(PrgState currentPrg) {
        ISemaphore semaphore=currentPrg.getSemaphore();

        List<TPair<Integer, Integer,ArrayList<Integer>>> SemaphoreTableList = new ArrayList<>();
        SemaphoreTableList.addAll(semaphore.getAll());
        SemaphoreTableView.setItems(FXCollections.observableList(SemaphoreTableList));
        SemaphoreTableView.refresh();
    }

    private void PopulateLockTable(PrgState currentPrg) {
        ILock locktable = currentPrg.getLock();

        List<Pair<Integer, Integer>> LockTableList = new ArrayList<>();
        LockTableList.addAll(locktable.getAll());
        LockTableView.setItems(FXCollections.observableList(LockTableList));
        LockTableView.refresh();
    }

    private PrgState getPrgState() {
        if (PrgStateIdsContent.getSelectionModel().getSelectedIndex() == -1)
            return null;

        int currentId = PrgStateIdsContent.getSelectionModel().getSelectedItem();
        return controller.getRepository().getPrgStateId(currentId);
    }
}
