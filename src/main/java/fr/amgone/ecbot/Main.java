package fr.amgone.ecbot;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Config config = new Config(Paths.get("config.toml"));
    }
}
