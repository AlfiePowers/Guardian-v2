package com.guardian.commands;

import com.guardian.Command;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public class SubredditImage implements Command {
    private final String HELP = "Usage: !reddit [subreddit]";
    private String[] message;
    RedditClient client;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        message = event.getMessage().getContent().toString().split("\\s+");
        for(int i = 0; i < 1; i++){
            if(message[i] == "!reddit"){
                return;
            }else{
                    UserAgent agent = UserAgent.of("desktop", "com.guardian.commands", "v0.01", "Guardian_bot");
                    Credentials credentials = Credentials.script("Guardian_bot", "2vdeN6kFq9j8", "ozpxmQhFlNnadw", "5QE_MY9QsO0r2LilBG8Qv9Q0CAE");
                    client = new RedditClient(agent);
                try {
                    OAuthData authData = client.getOAuthHelper().easyAuth(credentials);
                    client.authenticate(authData);
                } catch (OAuthException e) {
                    e.printStackTrace();
                }
                getRandomSubreddit(event);
            }
        }
    }
    public void getRandomSubreddit(MessageReceivedEvent event){
        SubredditPaginator sp = new SubredditPaginator(client);
        Submission sub = client.getRandomSubmission(message[1]);
        if(sub.isSelfPost() == false && sub.getUpvoteRatio() > 0.75 && sub.getScore() > 5){
            EmbedBuilder status_builder = new EmbedBuilder();
            status_builder.setImage(sub.getUrl());
            status_builder.setColor(Color.green);
            status_builder.setTitle("Random post from /r/" + message[1] + "!");
            status_builder.setFooter("This is a random post from: " + sub.getPermalink(), null);
            MessageEmbed embed = status_builder.build();
            MessageBuilder mbuilder = new MessageBuilder();
            mbuilder.setEmbed(embed);
            status_builder.addField("[Title] " + sub.getTitle().toString(),"[Upvotes] " + sub.getScore() + " At: " + (sub.getUpvoteRatio() * 100) + "% upvote percentage" , false);
            Message message = mbuilder.build();
            event.getChannel().sendMessage(message).queue();
        }else{
            getRandomSubreddit(event);
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
