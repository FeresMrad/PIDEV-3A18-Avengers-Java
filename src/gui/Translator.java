package gui;

import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Translator {
    public static void main(String[] args) throws IOException {
        String sourceLanguage = "en";
        String targetLanguage = "fr";
        String text = "What is your name?";
        String apiKey = "72a1860d84msh15024c99fb7e206p11200bjsncc83812f32eb";

        RequestBody body = new FormBody.Builder()
                .add("source_language", sourceLanguage)
                .add("target_language", targetLanguage)
                .add("text", text)
                .build();

        Request request = new Request.Builder()
                .url("https://text-translator2.p.rapidapi.com/translate")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
        }
    }
}
