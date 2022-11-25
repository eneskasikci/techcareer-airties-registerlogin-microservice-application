package com.example.registerloginapp.ui;

import com.example.registerloginapp.errors.ApiResult;
import com.google.gson.JsonElement;
import org.springframework.http.ResponseEntity;

public interface IDiaryApi {

    //SAVE
    ApiResult savePostToApi(JsonElement jsonElement);

    // DELETE
    ResponseEntity<?> deletePostIfUserIsTheOwner(JsonElement jsonElement);

    // LIST
    ResponseEntity<?> listDiaryPostFromUser(String username);

    // UPDATE
    // Post of the user who is logged-in
    ResponseEntity<?> updateDiaryPostFromRequest(JsonElement jsonElement);
}
