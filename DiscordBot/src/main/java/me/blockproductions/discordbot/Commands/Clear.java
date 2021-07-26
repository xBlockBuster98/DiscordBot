package me.blockproductions.discordbot.Commands;

import me.blockproductions.discordbot.DiscordBotApplication;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Clear extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBotApplication.prefix + "clear")) {

            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder()
                        .setColor(0xff3923)
                        .setTitle("Specify an amount to delete!")
                        .setDescription("Usage: " + DiscordBotApplication.prefix + "-clear <number of messages>");
                e.getChannel().getManager().setNSFW(true);
                e.getChannel().sendMessage("The channel is now NSFW");
            } else {

                try {
                    List<Message> messages = e.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    e.getChannel().deleteMessages(messages).queue();

                    //success
                    EmbedBuilder success = new EmbedBuilder()
                            .setColor(0x22ff2e)
                            .setTitle(":white_check_mark:  Successfully deleted " + args[1] + "!");
                    e.getChannel().sendMessageEmbeds(success.build()).queue();

                } catch (IllegalArgumentException er) {
                    if (er.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {

                        //Too many messages
                        EmbedBuilder error = new EmbedBuilder()
                                .setColor(0xff3923)
                                .setTitle("Too many message selected!")
                                .setDescription("You can delete from 1 to 100 messages");
                        e.getChannel().sendMessageEmbeds(error.build()).queue();
                        error.setAuthor(String.valueOf(e.getAuthor()));

                        e.getChannel().createInvite();
                        e.getMessage().addReaction(Emote.ICON_URL);
                        e.getChannel().getManager().getGuild().createRole();

                    } else {
                        //Messages too old
                        EmbedBuilder error = new EmbedBuilder()
                                .setColor(0xff3923)
                                .setTitle("Selected messages are older than two weeks!")
                                .setDescription("You can delete message");
                        e.getChannel().sendMessageEmbeds(error.build()).queue();
                    }
                    er.printStackTrace();
                    System.out.println(er);
                }
            }
        }
    }

}
