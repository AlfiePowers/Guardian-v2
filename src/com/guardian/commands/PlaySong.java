package com.guardian.commands;

import com.guardian.Command;
import com.guardian.util.MusicMon;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.audio.player.Player;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.VoiceChannel;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.managers.AudioManager;
import net.dv8tion.jda.managers.GuildManager;
import net.dv8tion.jda.player.MusicPlayer;
import net.dv8tion.jda.player.Playlist;
import net.dv8tion.jda.player.source.AudioInfo;
import net.dv8tion.jda.player.source.AudioSource;
import net.dv8tion.jda.player.source.RemoteSource;

import java.util.*;

public class PlaySong implements Command {
    private JDA api;
    private String message;
    public static Guild requestGuild;
    private Map<String, Player> players = new HashMap<>();
    public static List<AudioSource> queue;
    public static AudioManager am;
    public static MusicPlayer mp;
    private Playlist playlist;
    private ArrayList<AudioSource> sources = new ArrayList<>();
    private ArrayList<String> sources_loc = new ArrayList<>();

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        am = event.getGuild().getAudioManager();

        if (event.getMessage().getContent().equals("!play")) {
            event.getTextChannel().sendMessage("Error! Missing Parameters! No URL / File to play specified!");
        } else {
            message = event.getMessage().getContent();
            String url = message.replaceAll("!play", "");
            requestGuild = event.getGuild();
            VoiceChannel userRequest = requestGuild.getVoiceStatusOfUser(event.getAuthor()).getChannel();
            if (userRequest == null) {
                event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " - You are not connected to a Voice channel - " +
                        "Please join a voice Channel that the bot can access to play Music!");
            } else if (userRequest != null) {
                VoiceChannel channel = userRequest;
                if (requestGuild.getAudioManager().isConnected() == false) {
                    am.openAudioConnection(channel);
                    //requestGuild.getAudioManager().openAudioConnection(channel);
                }
                try {
                    playlist = Playlist.getPlaylist(url);

                    RemoteSource source = new RemoteSource(url);
                    AudioInfo info = new AudioInfo();
                    playlist = Playlist.getPlaylist(url);


                    // THIS HAS TO BE A CHECK - TL;DR: Without this, the bot will take the last song requested and overwrite the list.
                    if (sources.isEmpty()) {
                        sources = new ArrayList<>(playlist.getSources());
                        sources_loc = new ArrayList<>();
                        // Music Player is defined here
                        mp = new MusicPlayer();
                    }

                    /*
                    if(mp.isPlaying() == false){
                        for (int i = 0; i < sources.size(); i++){
                            sources.remove(i);
                        }
                    }
                    */

                    // Starts the monitor thread - Just used to monitor the status of the Music player - Offloaded to a thread so I don't worry about it
                    new Thread() {
                        @Override
                        public void run() {
                            new MusicMon();
                        }
                    }.start();

                    if (sources.size() == 1) {
                        sources.add((sources.size() - 1), source);
                        sources_loc.add(requestGuild.getId().toString());
                        System.out.println(sources_loc.get(sources_loc.size() - 1));
                        event.getTextChannel().sendMessage("Added " + source.getInfo().getTitle() + " to the queue as requested by " + event.getAuthor().getAsMention());
                        //Music Player section
                        for (int x = 0; x < sources.size(); x++) {
                            try {
                                if (sources_loc.get(x) == event.getGuild().getId().toString()) {
                                    mp.getAudioQueue().add(sources.get(x));
                                    mp.setVolume(0.5f);
                                    queue = mp.getAudioQueue();
                                    mp.play();
                                    am.setSendingHandler(mp);
                                    sources.remove(x);
                                    sources_loc.remove(x);
                                }
                            } catch (IndexOutOfBoundsException e) {
                                    /*
                                    I HAVE NO IDEA WHY IT IS DOING THIS!
                                    For some reason Index is equal to the size and it hates that
                                    If you know how to fix it, please tell me
                                    This is an empty Catch so the console isn't spammed with an error that has no real issue
                                    */
                            }
                        }
                    } else {
                        sources.add((sources.size() + 1), source);
                        event.getTextChannel().sendMessage("Added " + source.getInfo().getTitle() + "to the queue as requested by " + event.getAuthor().getAsMention());
                        //Music Player section
                        mp.getAudioQueue().add(sources.get(0));
                        mp.setVolume(0.5f);
                        queue = mp.getAudioQueue();
                        mp.play();
                        am.setSendingHandler(mp);
                        sources.remove(0);
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public String help() {
        return "Usage: !play [url]";
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}