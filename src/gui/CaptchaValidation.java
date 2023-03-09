package gui;
import java.io.IOException;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CaptchaValidation {

    private static final String API_URL = "https://image-captcha-solver.p.rapidapi.com/recognizeUrl";
    private static final String API_KEY = "ce2e7d9603msh56d0a5daca1476bp168843jsnd04ec9527f91";
    private static final String API_HOST = "image-captcha-solver.p.rapidapi.com";
    
    public static boolean isCaptchaValid(TextField captchatext, ImageView captchaImageView) {
        OkHttpClient client = new OkHttpClient();
        
        MediaType mediaType = MediaType.parse("application/json");
        String imageUrl = captchaImageView.getProperties().get("image").toString();
        String value = "{\r\n    \"url\": \"" + imageUrl + "\"\r\n}";
        RequestBody body = RequestBody.create(mediaType, value);
        Request request = new Request.Builder()
            .url(API_URL)
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", API_HOST)
            .build();
        
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            return captchatext.getText().equals(responseString.trim());
        } catch (IOException e) {
            System.err.println("Error executing request: " + e.getMessage());
            return false;
        }
    }
    
}
