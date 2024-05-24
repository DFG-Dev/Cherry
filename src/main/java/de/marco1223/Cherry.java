package de.marco1223;

import de.marco1223.handlers.DatabaseHandler;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDAInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.internal.utils.JDALogger;

public class Cherry {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().directory("./").load();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.connect(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));

        JDABuilder builder = JDABuilder.createDefault(dotenv.get("BOT_TOKEN"));
        JDA jda = builder.build();

        JDALogger.getLog(Cherry.class).info("-".repeat(20) + "Bot Information" + "-".repeat(20));
        JDALogger.getLog(Cherry.class).info("Java version: " + System.getProperty("java.version"));
        JDALogger.getLog(Cherry.class).info("JDA version: " + JDAInfo.VERSION);
        JDALogger.getLog(Cherry.class).info("Shard latency: " + jda.getGatewayPing() + "ms");
        JDALogger.getLog(Cherry.class).info("Guild count: " + jda.getGuilds().size());
        JDALogger.getLog(Cherry.class).info("User count: " + jda.getGuilds().stream().mapToInt(Guild::getMemberCount).sum());
        JDALogger.getLog(Cherry.class).info("-".repeat(54));

    }
}