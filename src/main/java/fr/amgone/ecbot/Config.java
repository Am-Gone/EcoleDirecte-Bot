package fr.amgone.ecbot;

import com.moandjiezana.toml.Toml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Config {
    private static final Logger logger = LogManager.getLogger(Config.class);

    private final String discordToken;

    public Config(Path path) {
        verifyConfigFile(path);

        Toml configFile = new Toml().read(path.toFile());

        discordToken = configFile.getString("discord-token");
        logger.info(discordToken);
    }

    private void verifyConfigFile(Path path) {
        InputStream defaultConfigLocation = getClass().getClassLoader().getResourceAsStream("config.toml");
        if (defaultConfigLocation == null) {
            logger.error("The config file template could not be found in the jar. Please verify that the jar is not corrupted.");
            System.exit(1);
        }

        if(!Files.exists(path)) {
            try {
                Files.copy(defaultConfigLocation, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException exception) {
                logger.error("Couldn't create the config file.", exception);
                System.exit(1);
            }

            logger.warn("The config file has been created. Please fill the file.");
            System.exit(1);
        }
    }

    public String getDiscordToken() {
        return discordToken;
    }
}
