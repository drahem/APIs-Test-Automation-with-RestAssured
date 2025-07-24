package utils;

import org.yaml.snakeyaml.Yaml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class YamlDataLoader {
    private static final Yaml yaml = new Yaml();
    private static final Logger logger = LoggerFactory.getLogger(YamlDataLoader.class);

    public static List<Map<String, Object>> loadTestCases(String filePath) {
        String resourcePath = "testData/" + filePath;

        try (InputStream input = YamlDataLoader.class.getClassLoader()
                .getResourceAsStream(resourcePath)) {
            logger.info("file content: {}", input.toString());
            if (input == null) {
                throw new RuntimeException("Cannot find resource: " + resourcePath + 
                    ". Please ensure the file exists in src/main/resources/" + resourcePath);
            }

            // Load root map and extract testCases list
            Map<String, Object> root = yaml.load(input);
            if (root == null) {
                throw new RuntimeException("YAML file is empty or invalid: " + filePath);
            }
            
            Object testCases = root.get("testCases");
            if (testCases == null) {
                throw new RuntimeException("No 'testCases' key found in YAML file: " + filePath);
            }
            
            return (List<Map<String, Object>>) testCases;

        } catch (ClassCastException e) {
            throw new RuntimeException("Invalid YAML structure in " + filePath + 
                ". Expected 'testCases' to be a list of maps", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML from " + filePath, e);
        }
    }
}