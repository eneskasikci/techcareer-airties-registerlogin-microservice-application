package com.example.registerloginapp.ui.impl;

import com.example.registerloginapp.ApiError.ApiResult;
import com.example.registerloginapp.apiservices.IDiaryAppService;
import com.example.registerloginapp.security.services.UserDetailsImpl;
import com.example.registerloginapp.ui.IDiaryApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("gateway/diary")
public class DiaryApiImpl implements IDiaryApi {

    //Injection
    private final IDiaryAppService dailyService;
    private static final String PATH = "gateway/diary";

    // CREATE
    // Post of the user who is logged in.
    //http://localhost:3333/gateway/diary/createDiary  ==> POST
    @Override
    @PostMapping("/createDiary")
    public ApiResult savePostToApi(@RequestBody JsonElement jsonElement) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("request_diaryUserName").getAsString())){
            dailyService.createDiaryPost(jsonElement);
            return new ApiResult(200, "Diary post successfully created.", PATH);
    }else {
            return new ApiResult(400, "You can not create a post for another user.", PATH);
        }
    }
    // DELETE
    // Post of the user who is logged in.
    //http://localhost:3333/gateway/diary/deleteDiaryPost  ==> DELETE
    @Override
    @DeleteMapping("/deleteDiaryPost")
    public ApiResult deletePostIfUserIsTheOwner(@RequestBody JsonElement jsonElement) {
        dailyService.deleteUserPost(jsonElement);
        return new ApiResult(200, "If the username you have given matches the owner of the postID, it is deleted.", PATH);
    }
    // LIST
    // Posts of the user who is logged in.

    // UPDATES
    // Post of the user who is logged in.
}

