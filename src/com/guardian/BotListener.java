package com.guardian;


import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.logging.Logger;

public class BotListener extends ListenerAdapter {
    public static String init_text;
    public static String init_text1;
    private static final Logger logger = Logger.getAnonymousLogger();
    JDA api;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        String botname = "@" + event.getJDA().getSelfUser().getName();
        if(event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContent());
        }else if(message.getContent().contains(botname)){
                init_text = event.getMessage().getContent();
                init_text1 = init_text.replace("@" + event.getJDA().getSelfUser().getName(), "");
                Main.handleConversation(event);
        }else{
            System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                    event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                    event.getMessage().getContent());
            Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));

        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        logger.info("Logged in as: " + event.getJDA().getSelfUser().getName());
    }
}
