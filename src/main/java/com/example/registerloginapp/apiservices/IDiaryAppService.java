package com.example.registerloginapp.apiservices;

import com.google.gson.JsonElement;

public interface IDiaryAppService {
    //SAVE
    JsonElement createDiaryPost(JsonElement jsonElement);
    //DELETE
    JsonElement deleteUserPost(JsonElement jsonElement);
}
