package utils;

import java.util.Map;

public class AllureUtils {
    public static String formatMap(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("\n  ").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        sb.append("\n}");
        return sb.toString();
    }
}