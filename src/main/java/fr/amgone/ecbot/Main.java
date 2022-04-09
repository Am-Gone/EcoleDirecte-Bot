package fr.amgone.ecbot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.nio.file.Paths;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = new Config(Paths.get("config.toml"));

        logger.info("Connecting to discord...");
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken(config.getDiscordToken())
                .login().join();
        logger.info("Done!");

    }
}
