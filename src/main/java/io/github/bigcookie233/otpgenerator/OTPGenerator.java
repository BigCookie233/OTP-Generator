package io.github.bigcookie233.otpgenerator;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "otpgenerator",
        name = "OTPGenerator",
        version = "1.0",
        authors = {"Bigcookie233"}
)
public class OTPGenerator {
    private final ProxyServer proxy;
    public final Logger logger;
    public static GeneratorConfig config;

    @Inject
    public OTPGenerator(ProxyServer proxy, Logger logger, GeneratorConfig generatorConfig) {
        this.proxy = proxy;
        this.logger = logger;
        config = generatorConfig;
        CommandManager commandManager = this.proxy.getCommandManager();
        commandManager.register("token", new TokenCommand());
        this.logger.info("Registered commands");
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
    }
}
