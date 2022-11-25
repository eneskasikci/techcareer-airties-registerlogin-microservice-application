package com.example.registerloginapp.ui.impl;

import com.example.registerloginapp.errors.ApiResult;
import com.example.registerloginapp.apiservices.IDiaryAppService;
import com.example.registerloginapp.security.services.UserDetailsImpl;
import com.example.registerloginapp.ui.IDiaryApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("gateway/diary")
public class DiaryApiImpl implements IDiaryApi {

    //Injection
    private final IDiaryAppService diaryAppService;
    private static final String PATH = "gateway/diary";

    // CREATE
    // Post of the user who is logged in.
    //http://localhost:3333/gateway/diary/createDiary  ==> POST
    @Override
    @PostMapping("/createDiary")
    public ApiResult savePostToApi(@RequestBody JsonElement jsonElement) {
        // get logged in users username and compare it with the post request username.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("request_diaryUserName").getAsString())){
            diaryAppService.createDiaryPost(jsonElement);
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
        diaryAppService.deleteUserPost(jsonElement);
        return new ApiResult(200, "If the username you have given matches the owner of the postID, it is deleted.", PATH);
    }
    // LIST
    // Posts of the user who is logged in.
    @Override
    @GetMapping("/listDiaryPostFromUser/{username}")
    public ResponseEntity<?> listDiaryPostFromUser(@PathVariable(name = "username") String username) {
        // get logged in users username and compare it with the username given in the path variable.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(username)){
            return ResponseEntity.ok(diaryAppService.listDiaryPostFromUser(username));
        }else {
            return ResponseEntity.ok("You can not list posts of another user.");
        }
    }

    // UPDATES
    // Post of the user who is logged in.
}

