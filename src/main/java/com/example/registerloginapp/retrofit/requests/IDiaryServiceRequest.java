package com.example.registerloginapp.retrofit.requests;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

public interface IDiaryServiceRequest {

    //CREATE

    @POST("/api/diaryApp/posts/createDiaryPost")
    Call<JsonElement> dailySave(@Body JsonElement jsonElement);

    //DELETE
    @HTTP(method = "DELETE", path = "/api/diaryApp/posts/deletePostIfUserIsOwner", hasBody = true)
    Call<JsonElement> deletePostIfUserIsTheOwner(@Body JsonElement jsonElement);

}