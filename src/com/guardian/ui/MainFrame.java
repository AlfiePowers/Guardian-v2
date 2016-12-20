package com.guardian.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
public class MainFrame extends Application{
            @Override
            public void start(Stage stage) throws Exception{

                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                stage.setTitle("Guardian Application");
                stage.setScene(new Scene(root, 1366, 768));
                stage.toFront();
                stage.show();

            }
    public void main(String[]args) {
        Application.launch(args);

    }
}

