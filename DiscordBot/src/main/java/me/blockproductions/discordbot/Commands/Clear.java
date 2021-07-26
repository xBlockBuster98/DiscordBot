package me.blockproductions.discordbot.Commands;

import me.blockproductions.discordbot.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class Clear extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) throws NullPointerException {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBot.prefix + "clear")) {

            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder()
                        .setTitle("Specify an amount to delete!")
                        .setDescription("Usage: " + DiscordBot.prefix + "-clear <number of messages>")
                        .setAuthor(event.getMember().getEffectiveName())
                        .setColor(0xff3923);

                TextChannel welcomeChannel = event.getGuild().getTextChannelById("803258568031731733");

                welcomeChannel.sendMessageEmbeds(usage.build()).queue();
                event.getChannel().getManager().setNSFW(true);
                event.getChannel().sendMessage("The channel is now NSFW");
            } else {

                try {
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();

                    //success
                    EmbedBuilder success = new EmbedBuilder()
                            .setColor(0x22ff2e)
                            .setTitle(":white_check_mark:  Successfully deleted " + args[1] + "!");
                    event.getChannel().sendMessageEmbeds(success.build()).queue();

                } catch (IllegalArgumentException er) {
                    if (er.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")) {

                        //Too many messages
                        EmbedBuilder error = new EmbedBuilder()
                                .setColor(0xff3923)
                                .setTitle("Too many message selected!")
                                .setDescription("You can delete from 1 to 100 messages");
                        event.getChannel().sendMessageEmbeds(error.build()).queue();
                        error.setAuthor(String.valueOf(event.getAuthor()));

                        event.getChannel().createInvite();
                        event.getMessage().addReaction(Emote.ICON_URL);
                        event.getChannel().getManager().getGuild().createRole();

                    } else {
                        //Messages too old
                        EmbedBuilder error = new EmbedBuilder()
                                .setColor(0xff3923)
                                .setTitle("Selected messages are older than two weeks!")
                                .setDescription("You can delete message");
                        event.getChannel().sendMessageEmbeds(error.build()).queue();
                    }
                    er.printStackTrace();
                    System.out.println(er);
                }
            }
        }
    }
}
