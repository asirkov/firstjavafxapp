package javafxapp.api.security;

import javafxapp.api.config.ApiConfig;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class ApiPasswordEncoder {
    private final ApiConfig apiConfig = new ApiConfig();

    public String sha256(String base) {
        String secret = apiConfig.getSecret();
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
