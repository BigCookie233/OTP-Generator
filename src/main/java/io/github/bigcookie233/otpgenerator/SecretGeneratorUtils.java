package io.github.bigcookie233.otpgenerator;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.apache.commons.codec.binary.Base32;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecretGeneratorUtils {
    public static String calculateSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            Base32 encoder = new Base32();
            return encoder.encodeAsString(digest);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            return "";
        }
    }

    public static String generateCode(String secret) throws CodeGenerationException {
        CodeGenerator codeGenerator = new DefaultCodeGenerator();
        TimeProvider timeProvider = new SystemTimeProvider();
        long currentBucket = Math.floorDiv(timeProvider.getTime(), 30L);
        return codeGenerator.generate(secret, currentBucket);
    }
}
