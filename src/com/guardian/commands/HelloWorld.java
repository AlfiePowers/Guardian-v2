package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class HelloWorld implements Command {
    private final String HELP = "Usage: !helloworld";
    JDA jda;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("/tts Hello World");
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
