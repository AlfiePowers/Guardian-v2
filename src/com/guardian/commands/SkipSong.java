package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SkipSong implements Command {
    private final String HELP = "Usage: !skip";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        String[] command = event.getMessage().getContent().split(" ", 2);
        if (guild != null) {
            if ("!skip".equals(command[0])) {
                PlaySong.musicManager.scheduler.nextTrack();
                PlaySong.channel.sendMessage("Skipped to next track.").queue();
            }
        }

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
