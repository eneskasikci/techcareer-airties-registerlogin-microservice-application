package com.example.registerloginapp.retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;

@Log4j2
public class RetrofitCommonGenerics {

    public static <T> T retrofitGenerics(Call<T> request) {
        try {
            //retrofit
            Response<T> response = request.execute();
            if (!response.isSuccessful()) {
                assert response.errorBody() != null;
                log.error("Request Failed: {}", response.errorBody().string());
            }
            return response.body();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new RuntimeException(ioException.getCause());
        }
    }

    // Getting an error.
    // JSON parse error: Cannot deserialize value of type `byte[]`
    // Illegal character '[' baseImage
//    public static JsonElement convertStringToJson(String blogTitle, String blogContent, String userName, Long blogUserId, byte[] blogImage) {
//
//        try {
//            String json = "{\"request_blogTitle\":\"" + blogTitle +
//                    "\",\"request_blogContent\":\"" + blogContent +
//                    "\",\"request_userName\":\"" + userName +
//                    "\",\"request_blogUserId\":\"" + blogUserId +
//                    "\",\"request_blogImage\":\"" + () + "\"}";
//            return JsonParser.parseString(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e.getCause());
//        }
//    }
}
