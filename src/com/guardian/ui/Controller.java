package com.guardian.ui;

import com.guardian.InitMain;
import com.guardian.Main;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;
import net.dv8tion.jda.JDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements EventHandler{
    private static JDA jda;
    public void startButtonClicked(){
        System.out.println("Start init");
        try {
            jda.shutdown(true);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void stopButtonClicked(){
        System.out.println("Stop init");

    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    @Override
    public void handle(Event event) {

        if(event.getSource() == "TokenEnter"){
            System.out.println("Start pressed");
        }
    }
}
