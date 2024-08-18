package io.github.bigcookie233.otpgenerator;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class TokenCommand implements SimpleCommand {
    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        if (source instanceof Player player) {
            String playerSecret = SecretGeneratorUtils.calculateSHA256(OTPGenerator.config.secret + player.getUsername());
            try {
                String code = SecretGeneratorUtils.generateCode(playerSecret);
                source.sendMessage(MiniMessage.miniMessage().deserialize(OTPGenerator.config.successMsg.replace("%code%", code)));
            } catch (CodeGenerationException e) {
                throw new RuntimeException(e);

            }
        } else {
            source.sendMessage(MiniMessage.miniMessage().deserialize(OTPGenerator.config.failedMsg));
        }
    }
}
