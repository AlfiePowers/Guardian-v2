package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class QueueInfo implements Command {
    private final String HELP = "Usage: !helloworld";
    JDA jda;

    private int queuesize;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        event.getTextChannel().sendMessage("Current Queue size is: " + queuesize);

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
