package com.guardian.util;


import com.guardian.Main;
import com.guardian.commands.PlaySong;
import net.dv8tion.jda.player.source.AudioSource;

import java.util.ArrayList;
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
                Main.manager.setGame(SetBotGame.list.get(random.nextInt(SetBotGame.list.size())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
