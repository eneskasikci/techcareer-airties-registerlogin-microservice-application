package com.example.registerloginapp.retrofit;

import lombok.extern.log4j.Log4j2;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
@Log4j2
public class RetrofitCommonGenerics {

    public static <T> T retrofitGenerics(Call<T> request){
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
            throw  new RuntimeException(ioException.getCause());
        }
    }
}
