package api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentReader {
    private final String baseUrl;
    private final String bearerToken = "";  // You can later extend this to read token if needed

    public EnvironmentReader() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read config.properties file", e);
        }

        String defaultEnv = prop.getProperty("default.env");
        if (defaultEnv == null || defaultEnv.isEmpty()) {
            throw new RuntimeException("default.env is not defined in config.properties");
        }

        String apiKey = defaultEnv + ".base.api";
        String resolvedBaseUrl = prop.getProperty(apiKey);

        if (resolvedBaseUrl == null || resolvedBaseUrl.isEmpty()) {
            throw new RuntimeException("Base API URL for environment [" + defaultEnv + "] is missing in config.properties");
        }

        this.baseUrl = resolvedBaseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBearerToken() {
        return bearerToken;  // Placeholder for future token handling
    }
}
