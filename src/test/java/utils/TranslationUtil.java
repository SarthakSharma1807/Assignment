package utils;

import okhttp3.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

public class TranslationUtil {
    private static final String RAPIDAPI_HOST = "rapid-translate-multi-traduction.p.rapidapi.com";
    private static final String RAPIDAPI_KEY = "3a92c55432msh8b43aff9ba94d5ap1ad587jsna0f4561e2a39"; // Replace with your RapidAPI key

    public static String translateText(String text, String fromLang, String toLang) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        JsonArray textArray = new JsonArray();
        textArray.add(text);

        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("from", fromLang);
        jsonBody.addProperty("to", toLang);
        jsonBody.add("q", textArray);

        RequestBody body = RequestBody.create(jsonBody.toString(), mediaType);
        Request request = new Request.Builder()
            .url("https://rapid-translate-multi-traduction.p.rapidapi.com/t")
            .post(body)
            .addHeader("x-rapidapi-key", RAPIDAPI_KEY)
            .addHeader("x-rapidapi-host", RAPIDAPI_HOST)
            .addHeader("Content-Type", "application/json")
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (response.isSuccessful() && responseBody != null) {
                String responseBodyString = responseBody.string();
                JsonArray translationsArray = JsonParser.parseString(responseBodyString).getAsJsonArray();
                return translationsArray.get(0).getAsString();
            } else {
                throw new IOException("Translation API call failed: " + response.message());
            }
        }
    }
}
