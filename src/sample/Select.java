package sample;


import Model.ADT.*;
import Model.Expression.*;
import Model.PrgState;
import Model.Stmt.CmpStmt;
import Model.Stmt.IStmt;
import Repository.*;
import Controller.*;
import View.Interpreter;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Select implements Initializable {
    private List<IStmt> programStatements;
    private Runform mainWindowController;

    @FXML
    private ListView<String> programListView;

    @FXML
    private Button executeButton;

    public void setMainWindowController(Runform mainWindowController) {
        this.mainWindowController = mainWindowController;

    }

    private void buildProgramStatements() {
        IStmt ex1 = Interpreter.Ex1();
        IStmt ex2 = Interpreter.Ex2();
        IStmt ex3 = Interpreter.Ex4();
        IStmt ex4 = Interpreter.Ex3();
        IStmt ex5 = Interpreter.Ex5();
        IStmt ex6 = Interpreter.Ex6();
        IStmt ex7 = Interpreter.Ex7();
        IStmt ex8 = Interpreter.Ex8();
        IStmt ex9 = Interpreter.Ex9();
        IStmt ex10 = Interpreter.Ex10();
        IStmt ex11 = Interpreter.Ex11();
        programStatements = new ArrayList<>(Arrays.asList( ex7,ex10));
    }

    private List<String> getStringRepresentations() {
        return programStatements.stream().map(IStmt::toString).collect(Collectors.toList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildProgramStatements();
        programListView.setItems(FXCollections.observableArrayList(getStringRepresentations()));

        executeButton.setOnAction(actionEvent -> {
            int index = programListView.getSelectionModel().getSelectedIndex();

            if (index < 0)
                return;

            PrgState initialProgramState = new PrgState(new MyStack<>(), new MyDict<>(), new MyList<>(), new MyDict<>(), new Heap<>(new HashMap<>()), programStatements.get(index), index,
                    new Semaphore(),new Lock(),new CyclicBarrier());
            IRepo repository = new Repo(initialProgramState, "log2.txt");
            Controller ctrl = new Controller(repository);

            mainWindowController.setController(ctrl);

            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        });
    }
}