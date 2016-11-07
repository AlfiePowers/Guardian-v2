package com.guardian;

import com.guardian.ui.MainFrame;
import com.guardian.ui.Splash;
import com.guardian.ui.Splash;
import com.guardian.util.SchedulerService;
import com.guardian.util.SetBotGame;
import javafx.application.Application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.ThreadPoolExecutor;

public class InitMain{
    public static Thread core;
    public static void main(String[]args){
        new Thread(){
            @Override
            public void run(){
                new Main();
            }
        }.start();


    }
}
