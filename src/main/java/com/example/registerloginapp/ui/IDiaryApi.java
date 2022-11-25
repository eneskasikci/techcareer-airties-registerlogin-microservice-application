package com.example.registerloginapp.ui;

import com.example.registerloginapp.ApiError.ApiResult;
import com.google.gson.JsonElement;

public interface IDiaryApi {

    //SAVE
    ApiResult savePostToApi(JsonElement jsonElement);

    // DELETE
    ApiResult deletePostIfUserIsTheOwner(JsonElement jsonElement);

}
