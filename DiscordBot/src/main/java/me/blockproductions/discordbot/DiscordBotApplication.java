package me.blockproductions.discordbot;

import me.blockproductions.discordbot.Commands.Clear;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiscordBotApplication {

    public static JDA jda;
    public static String prefix = "-";

    public static void main(String[] args) {

        DiscordApi api = new DiscordApiBuilder().
                setToken("ODY4NDkxMTM3MTQ3NjIxNDA2.YPwbZg.x-2BzQ2tRXRRxN1x_rtK2w0QEpo")
                .login()
                .join();

        jda.addEventListener(new Clear());

        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!ping")) {
                /*
                EmbedBuilder embed = new EmbedBuilder()
                .setColor(0xff3923)
                        .setTitle("Yoyoyo, this is the title!")
                        .setDescription("Description")
                        .addField("Field name", "Field value", true);
                 */

                event.getChannel().sendMessage("Pong!");
            }
        });
        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
