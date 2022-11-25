package com.example.registerloginapp.apiservices.impl;

import com.example.registerloginapp.apiservices.IDiaryAppService;
import com.example.registerloginapp.retrofit.RetrofitCommonGenerics;
import com.example.registerloginapp.retrofit.requests.IDiaryServiceRequest;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Log4j2

@Service
public class DiaryAppService implements IDiaryAppService {

    // injection
    private final IDiaryServiceRequest iDiaryServiceRequest;

    // SAVE
    @Override
    public JsonElement createDiaryPost(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(iDiaryServiceRequest.dailySave(jsonElement));
    }

    // DELETE
    @Override
    public JsonElement deleteUserPost(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(iDiaryServiceRequest.deletePostIfUserIsTheOwner(jsonElement));
    }

}