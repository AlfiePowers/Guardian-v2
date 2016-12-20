package com.guardian;

import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

import java.util.logging.Logger;

public class BotListener extends ListenerAdapter {
    public static String init_text;
    public static String init_text1;
    private static final Logger logger = Logger.getAnonymousLogger();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId())
            Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));

        String botname = "@" + event.getJDA().getSelfInfo().getUsername();
        if (event.getMessage().getContent().contains(botname) && event.getMessage().getAuthor().getId() != event.getJDA().getSelfInfo().getId()) {
            init_text = event.getMessage().getContent();
            init_text1 = init_text.replace("@" + event.getJDA().getSelfInfo().getUsername(), "");
            Main.handleConversation(event);
        }
    }

    @Override
    public void onReady(ReadyEvent event) {
        logger.info("Logged in as: " + event.getJDA().getSelfInfo().getUsername());
    }
}
