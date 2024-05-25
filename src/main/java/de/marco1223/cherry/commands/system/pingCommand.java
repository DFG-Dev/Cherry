package de.marco1223.cherry.commands.system;

import de.marco1223.cherry.interfaces.ISlashCommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

public class pingCommand implements ISlashCommandHandler {

    /*
    *
    * NOTE: THIS IS FOR A MATE WHO IS LEARNING JAVA AND JDA. THIS FILE SHOULD BE USED AS A REFERENCE.
    *
    * Let's start by implementing the execute method.
    * You can see that the execute method takes a SlashCommandInteractionEvent as a parameter.
    * This event is fired when a user interacts with a slash command.
    *
    * IMPORATANT: READ THE DOCS THERE ARE ALSO SOME EXAMPLES (https://docs.jda.wiki/)
    *
    * */
    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        event.deferReply(true).queue(); // Acknowledge the command, and send a reply later (SHOWING THINKING IN THE CHAT). IS IT NECESSARY? NO, BUT IT'S A GOOD PRACTICE.

        // Creating an embed
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0xeb5762); // Setting the color of the embed
        embed.setTitle("Pong! 🏓"); // Setting the title of the embed
        embed.setDescription("The bot is online and ready to use!"); // Setting the description of the embed
        embed.addField("API Latency", event.getJDA().getGatewayPing() + "ms", true); // Adding a field to the embed
        embed.setFooter("Requested by " + event.getUser().getAsTag(), event.getUser().getAvatarUrl()); // Setting the footer of the embed
        embed.setTimestamp(event.getTimeCreated()); // Setting the timestamp of the embed
        embed.build(); // Building the embed. There is alot more you can do with embeds, check the docs for more information.

        event.getHook().sendMessageEmbeds(embed.build()).queue(); // Replying with the embed (SENDING THE EMBED IN THE CHAT). You should use event.replyEmbeds if you don't use deferReply.

    }

    @NotNull
    @Override
    public CommandData getCommandData() {
        return Commands
                .slash("ping", "Check the bot's latency") // Creating a new slash command with the name "ping" and the description "Check the bot's latency"
                .setGuildOnly(true); // Setting the command to only work in guilds
    }
}
