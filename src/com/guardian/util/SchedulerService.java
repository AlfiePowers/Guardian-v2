package com.guardian.util;


import com.guardian.Main;

import java.util.Random;

public class SchedulerService {
    public SchedulerService() {
        changeGame();
    }


    public void changeGame() {
        Thread changeGame = new Thread();
        changeGame.start();

        while (true) {
            try {
                Thread.sleep(1000 * (5 * 60));
                Random random = new Random();

                System.out.println("Setting as a random game name");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
