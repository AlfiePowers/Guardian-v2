package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.player.JDAPlayerInfo;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.util.concurrent.TimeUnit;

public class Status implements Command {
    private final String HELP = "Usage: !ping";
    JDA jda;
    JDAPlayerInfo player_api;
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String author = event.getMessage().getAuthor().getAsMention().toString();
        event.getTextChannel().sendMessage(author);
        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
        long uptime = rb.getUptime();
        long days = TimeUnit.MILLISECONDS.toDays((uptime));
        long hours = TimeUnit.MILLISECONDS.toHours((uptime));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
        event.getTextChannel().sendMessage("```-- Guardian Status -- \n " +
                "Name: " + event.getJDA().getSelfInfo().getUsername() + " (ID: " + event.getJDA().getSelfInfo().getId() +")\n " +
                "Uptime: " + days + " days, " +  hours + " hours, " + minutes + " minutes, " + (seconds - (minutes * 60)) + " seconds" +" ``` ");


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
