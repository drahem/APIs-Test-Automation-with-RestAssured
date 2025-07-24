package utils;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlHelper {

    public static HashMap<String, Object> getData(String yamlFilePath) {
        Yaml yaml = new Yaml();
        try (InputStream input = YamlDataLoader.class.getClassLoader()
                .getResourceAsStream(yamlFilePath)) {

            // Load the entire YAML structure
            Map<String, Object> yamlData = yaml.load(input);

            // Get the test cases list
            List<Map<String, Object>> testCases =
                    (List<Map<String, Object>>) yamlData.get("testCases");

            if (testCases == null || testCases.isEmpty()) {
                throw new RuntimeException("No test cases found in YAML");
            }

            // Get data from first test case (index 0)
            Map<String, Object> dataSection =
                    (Map<String, Object>) testCases.get(0).get("data");

            if (dataSection == null) {
                throw new RuntimeException("No data section found in test case");
            }

            // Convert to HashMap
            return new HashMap<>(dataSection);

        } catch (Exception e) {
            throw new RuntimeException("Error reading YAML data", e);
        }
    }
}