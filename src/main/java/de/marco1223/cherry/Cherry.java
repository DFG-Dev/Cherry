package de.marco1223.cherry;

import de.marco1223.cherry.commands.system.pingCommand;
import de.marco1223.cherry.handlers.DatabaseHandler;
import de.marco1223.cherry.handlers.SlashCommandHandler;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.internal.utils.JDALogger;

import java.sql.SQLException;

public class Cherry {
    public static void main(String[] args) throws InterruptedException, SQLException {

        Dotenv dotenv = Dotenv.configure().directory("./").load();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

        JDABuilder builder = JDABuilder.createDefault(dotenv.get("BOT_TOKEN"));
        builder.addEventListeners(registerCommands());
        JDA jda = builder.build();

        jda.awaitReady();

        JDALogger.getLog(Cherry.class).info("-".repeat(20) + "Bot Information" + "-".repeat(20));
        JDALogger.getLog(Cherry.class).info("Java version: " + System.getProperty("java.version"));
        JDALogger.getLog(Cherry.class).info("JDA version: " + JDAInfo.VERSION);
        JDALogger.getLog(Cherry.class).info("Postgres version: " + databaseHandler.getPostgresMetadata().getDatabaseProductVersion());
        JDALogger.getLog(Cherry.class).info("Shard latency: " + jda.getGatewayPing() + "ms");
        JDALogger.getLog(Cherry.class).info("Guild count: " + jda.getGuilds().size());
        JDALogger.getLog(Cherry.class).info("User count: " + jda.getGuilds().stream().mapToInt(Guild::getMemberCount).sum());
        JDALogger.getLog(Cherry.class).info("-".repeat(54));

        jda.getPresence().setActivity(Activity.playing("with IntelliJ IDEA"));

    }

    private static SlashCommandHandler registerCommands() {

        final var slashCommandHandler = new SlashCommandHandler();
        slashCommandHandler.addCommands(
                new pingCommand() // Add your commands here like this.
        );

        return slashCommandHandler;

    }

}