package com.example.registerloginapp.retrofit.requests;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

public interface IDiaryServiceRequest {

    //CREATE Post
    @POST("/api/diaryApp/posts/createDiaryPost")
    Call<JsonElement> dailySave(@Body JsonElement jsonElement);

    @GET("/api/diaryApp/posts/getAllPostsFromUser/{username}")
    Call<JsonElement> getAllDiaryPostsFromUser(@Path("username") String username);
    //DELETE
    @HTTP(method = "DELETE", path = "/api/diaryApp/posts/deletePostIfUserIsOwner", hasBody = true)
    Call<JsonElement> deletePostIfUserIsTheOwner(@Body JsonElement jsonElement);

}