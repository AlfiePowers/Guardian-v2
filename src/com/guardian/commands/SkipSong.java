package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.Permission;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class SkipSong implements Command {
    private final String HELP = "Usage: !skip";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        Permission permission;

        /*if(PlaySong.queue.isEmpty()){
            PlaySong.am.closeAudioConnection();
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " - Queue concluded");
        }else{
            PlaySong.mp.skipToNext();
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " - Skipped Song");
        }
        */
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
