package sample;

import Model.ADT.Heap;
import Model.ADT.MyDict;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.PrgState;
import Model.Stmt.CmpStmt;
import Model.Stmt.IStmt;
import Repository.IRepo;
import Repository.Repo;
import View.Interpreter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import Controller.Controller;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("runForm.fxml"));
        Parent mainWindow = mainLoader.load();

        Runform mainWindowController = mainLoader.getController();

        primaryStage.setTitle("Main Window");
        primaryStage.setScene(new Scene(mainWindow, 1600, 1000));
        primaryStage.show();
        FXMLLoader secondaryLoader = new FXMLLoader();
        secondaryLoader.setLocation(getClass().getResource("Select.fxml"));
        Parent secondaryWindow = secondaryLoader.load();

        Select selectWindowController = secondaryLoader.getController();
        selectWindowController.setMainWindowController(mainWindowController);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Select Window");
        secondaryStage.setScene(new Scene(secondaryWindow, 1000, 550));
        secondaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

