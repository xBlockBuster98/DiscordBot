package me.blockproductions.discordbot.Commands;

import me.blockproductions.discordbot.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Diocan extends ListenerAdapter {

    public void pajaccioCheEntra (GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBot.prefix + "diocan")) {

            if (args.length < 2) {
                EmbedBuilder usage = new EmbedBuilder()
                        .setTitle("Specify an amount to delete!")
                        .setDescription("Usage: " + DiscordBot.prefix + "-diocan <number of messages>")
                        .setAuthor(event.getMember().getEffectiveName())
                        .setColor(0xff3923);

                TextChannel welcomeChannel = event.getGuild().getTextChannelById("803258568031731733");

                welcomeChannel.sendMessageEmbeds(usage.build()).queue();
                event.getChannel().getManager().setNSFW(true);
                event.getChannel().sendMessage("The channel is now NSFW");
            }
        }

    }

}
