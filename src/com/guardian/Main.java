package com.guardian;

import com.google.code.chatterbotapi.*;
import com.guardian.commands.*;
import com.guardian.util.SchedulerService;
import com.guardian.util.SetBotGame;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.managers.AccountManager;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Main {
    String key;
    private static JDA jda;
    public static final CommandParser parser = new CommandParser();
    public static AccountManager manager;

    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public Main(){
        File file = new File("key.txt");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            key = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try{
            jda = new JDABuilder().addListener((new BotListener())).setBulkDeleteSplittingEnabled(false)
                    .setBotToken(key).buildBlocking();
            jda.setAutoReconnect(false);
        }catch(Exception e){
            e.printStackTrace();
        }
        commands.put("ping", new Ping());
        commands.put("helloworld", new HelloWorld());
        commands.put("status", new Status());
        commands.put("play", new PlaySong());
        commands.put("reddit", new SubredditImage());
        commands.put("skip", new SkipSong());
        commands.put("queue", new QueueInfo());
        commands.put("help", new Help());
        commands.put("commands", new CommandList());
        manager = jda.getAccountManager();
        //Start the Set-up
        new Thread(){
            @Override
            public void run(){
                try {
                    new SetBotGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run(){
                new SchedulerService();
            }
        }.start();
    }
    public static void handleCommand(CommandParser.CommandContainer cmd){

        if(commands.containsKey(cmd.invoke)){
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe){
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }else{
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
    public static void handleConversation(MessageReceivedEvent event){
        ChatterBotFactory factory = new ChatterBotFactory();
        try {
            ChatterBot bot = factory.create(ChatterBotType.CLEVERBOT);
            ChatterBotSession botsession = bot.createSession();
            String s = BotListener.init_text1;
            s = botsession.think(s);
            event.getTextChannel().sendMessage(event.getMessage().getAuthor().getAsMention().toString() + " " + s);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
