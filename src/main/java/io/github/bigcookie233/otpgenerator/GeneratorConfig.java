package io.github.bigcookie233.otpgenerator;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class GeneratorConfig {
    private final Path dataFolder;
    private final Path configPath;
    public final String secret;
    public final String successMsg;
    public final String failedMsg;

    @Inject
    public GeneratorConfig(@DataDirectory Path dataDirectory) {
        this.dataFolder = dataDirectory;
        this.configPath = dataDirectory.resolve("config.toml");

        try {
            saveDefaultConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Toml toml = loadConfig();

        this.secret = toml.getString("secret.key");
        this.successMsg = toml.getString("messages.success-message");
        this.failedMsg = toml.getString("messages.failed-message");
    }

    private void saveDefaultConfig() throws IOException {
        if (Files.notExists(dataFolder)) Files.createDirectory(dataFolder);
        if (Files.exists(configPath)) return;

        try (InputStream in = GeneratorConfig.class.getResourceAsStream("/config.toml")) {
            assert in != null;
            Files.copy(in, configPath);
        }
    }

    private File configFile() {
        return configPath.toFile();
    }

    private Toml loadConfig() {
        return new Toml().read(configFile());
    }
}
