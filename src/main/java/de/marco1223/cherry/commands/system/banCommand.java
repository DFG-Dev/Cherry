package de.marco1223.cherry.commands.system;

import de.marco1223.cherry.interfaces.ISlashCommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

public static void main(String[] args)
{
    /*JDA jda = JDABuilder.createLight("BOT_TOKEN_HERE", EnumSet.noneOf(GatewayIntent.class))
            .addEventListeners(new SlashBotExample())
            .build(); [Don't know if needed]*/

    CommandListUpdateAction commands = jda.updateCommands();

    commands.addCommands(
        Commands.slash("ban", "Ban a user from this server.")
            .addOptions(new OptionData(USER, "user", "ID of user")
                .setRequired(true))
            .addOptions(new OptionData(INTEGER, "del_days", "Delete messages from the number of days")
                .setRequiredRange(0, 7)) //DAYS
            .addOptions(new OptionData(STRING, "reason", "The ban reason to use (default: Banned by <user>)"))
            .setGuildOnly(true) 
            .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS))
    );

    @NotNull
    @Override
    public CommandData getCommandData() {

        final var slashCommandHandler = new SlashCommandHandler();
        slashCommandHandler.addCommands(
                new banCommand()
        );
        
        return slashCommandHandler;
                
    }
}
