package me.blockproductions.discordbot.Commands;

import me.blockproductions.discordbot.DiscordBotApplication;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Mute extends ListenerAdapter {

    public void onGuildReceiveMessage(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase(DiscordBotApplication.prefix + "mute")) {

        }
    }

}
