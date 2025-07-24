package utils;

import annotations.YamlTestCase;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class YamlDataProvider {

    @DataProvider(name = "yamlData")
    public static Object[][] provideTestData(Method method) {
        YamlTestCase annotation = method.getAnnotation(YamlTestCase.class);
        List<Map<String, Object>> testCases = loadTestCases(annotation.value());

        return testCases.stream()
                .map(testCase -> new Object[]{testCase.get("data")})
                .toArray(Object[][]::new);
    }

    private static List<Map<String, Object>> loadTestCases(String yamlFile) {
        Yaml yaml = new Yaml();
        try (InputStream input = YamlDataProvider.class.getClassLoader()
                .getResourceAsStream("testdata/" + yamlFile)) {

            Map<String, Object> yamlData = yaml.load(input);
            return (List<Map<String, Object>>) yamlData.get("testCases");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML data", e);
        }
    }
}