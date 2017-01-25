package com.guardian.util;


import net.dv8tion.jda.core.JDA;

public class MusicMon {
    JDA jda;

    public MusicMon() {
        songStatus();
    }

    public void songStatus() {
        Thread songStatus = new Thread();
        songStatus.start();

        /*while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (PlaySong.mp.isPlaying() == false) {
                if (PlaySong.am.isConnected() == true) {
                    PlaySong.am.closeAudioConnection();
                }
            }
        }
        */

    }
}
