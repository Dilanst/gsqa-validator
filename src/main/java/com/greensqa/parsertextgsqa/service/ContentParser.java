package com.greensqa.parsertextgsqa.service;
import com.google.gson.Gson;

public class ContentParser {
    public static String extractContent(String json) {
        Gson gson = new Gson();
        ChatCompletionResponse response = gson.fromJson(json, ChatCompletionResponse.class);
        return response.getChoices().get(0).getMessage().getContent();
    }
}
