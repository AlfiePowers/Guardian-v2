package com.guardian.util;

import com.guardian.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SetBotGame {
    public static List<String> list;
    public static boolean setup = false;
    int lineCount = 0;

    public SetBotGame() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("res/games.txt"));
        String str;

        list = new ArrayList<String>();
        while((str = in.readLine()) != null){
            list.add(str);
        }
        String[] stringArr = list.toArray(new String[0]);
        Random random = new Random();
        int rand = random.nextInt(list.size());
        Main.manager.setGame(list.get(rand));
        setup = true;
    }
}

