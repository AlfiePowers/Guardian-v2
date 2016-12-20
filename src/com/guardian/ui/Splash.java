package com.guardian.ui;

import javafx.application.Application;
import com.guardian.ui.MainFrame;
import javax.swing.*;

public class Splash {
    public Splash(){
        JFrame frame = new JFrame();
        frame.setSize(640, 360);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        JPanel contentPane = new JPanel();
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("C:\\Users\\power\\Pictures\\Discord\\Guardian_splash.png"));
        label.setSize(640, 360);
        contentPane.add(label);
        frame.add(contentPane);
        frame.setVisible(true);
        try {
            Thread.sleep(1000);
            frame.dispose();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Application.launch(MainFrame.class);
    }
}
