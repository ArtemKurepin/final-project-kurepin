package Ru.Avito.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Map;


public class AvitoJsonUtills {
    public static Map<String, Object> getHeaders(String id) {

        String jsonContent = null;
        try {
            jsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/Headers.json")));
        } catch (Exception e) {
            System.err.println("Файл не найден! Путь: " + Paths.get("Headers.json").toAbsolutePath());
            e.printStackTrace();
        }
        JsonObject root = JsonParser.parseString(jsonContent).getAsJsonObject();
        JsonObject temp = root.getAsJsonObject(id);
        Gson gson = new Gson();
        Map<String, Object> headersMap = gson.fromJson(temp, new TypeToken<Map<String, Object>>() {
        }.getType());

        return headersMap;
    }

    public static String getCookieFromFile(String category) {
        JSONObject obj = new JSONObject(Paths.get("Cookies.json"));
        String result = obj.optString(category);
        return result;
    }

    public static JSONObject readJsonFromFile(String filePath) throws Exception {
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(jsonString);
    }

    public static boolean areJsonSimilar(JSONObject expected, JSONObject actual) {
        return actual.similar(expected);
    }

}
