package io.github.bigcookie233.otpgenerator;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(
        id = "otpgenerator",
        name = "OTPGenerator",
        version = "1.3",
        authors = {"Bigcookie233"}
)
public class OTPGenerator {
    public static GeneratorConfig config;
    private final ProxyServer proxy;

    @Inject
    public OTPGenerator(ProxyServer proxy, GeneratorConfig generatorConfig) {
        this.proxy = proxy;
        config = generatorConfig;
        CommandManager commandManager = this.proxy.getCommandManager();
        commandManager.register("token", new TokenCommand());
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
    }
}
