package com.guardian.commands;

import com.guardian.Command;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.PatternSyntaxException;


public class SubredditImage implements Command {
    private final String HELP = "Usage: !helloworld";
    public String URL_raw;
    public String[] URL_FINAL;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {

        //When I wrote this, only God and I understood what I was doing
        //Now, Even God's given up and pissed off.
        //If you intend to edit this thinking "OH I CAN DO BETTER", Don't...
        //If you hate XML, Close down the file now. This ain't gonna be pretty...
        //Sincerely, me before I wrote this code...
        URL_raw = event.getMessage().getContent().toString();
        try{
        URL_FINAL = URL_raw.split("\\s+");
        }catch(PatternSyntaxException e){
            // I was never much good at catching in Sport... Not much change here...
            e.printStackTrace();
        }
        // Just gonna check there is a subreddit with that name - And that no other parameters have been passed.
        for (int i = 0; i < 1; i++){
            if(URL_FINAL[i] == "!image"){
                return;
            }else {
                String subreddit = URL_FINAL[1];
                System.out.println(subreddit);
                BufferedInputStream in = null;
                FileOutputStream fout = null;
                try {
                    URL url = new URL("http://www.reddit.com/r/" + subreddit + "/.xml");
                    URLConnection connection = url.openConnection();
                    Document doc = parseXML(connection.getInputStream());
                    NodeList descNodes = doc.getElementsByTagName("description");

                    for(int x =0; x < descNodes.getLength(); x++){
                        System.out.println(descNodes.item(i).getTextContent());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /*DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder
                */

            }
        }
        //URL subredditURL = new URL();
    }

    public Document parseXML(InputStream stream) throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }

        return doc;
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
