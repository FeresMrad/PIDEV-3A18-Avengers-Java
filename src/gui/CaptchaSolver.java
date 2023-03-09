package gui;
import okhttp3.*;

import java.io.IOException;

public class CaptchaSolver {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String value = "{\"url\": \"https://i.ibb.co/PWYYSm1/d1.jpg\"}";
        RequestBody body = RequestBody.create(mediaType, value);

        Request request = new Request.Builder()
                .url("https://image-captcha-solver.p.rapidapi.com/recognizeUrl")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("X-RapidAPI-Key", "72a1860d84msh15024c99fb7e206p11200bjsncc83812f32eb")
                .addHeader("X-RapidAPI-Host", "image-captcha-solver.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("Error: " + response.code() + " " + response.message());
        }
    }
}
