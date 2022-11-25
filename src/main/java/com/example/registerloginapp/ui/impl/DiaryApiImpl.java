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
    // Diary post of the user who is logged-in.
    //http://localhost:3333/gateway/diary/createDiary  ==> POST
    @Override
    @PostMapping("/createDiary")
    public ApiResult savePostToApi(@RequestBody JsonElement jsonElement) {
        // get logged-in users username and compare it with the post request username.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("request_diaryUserName").getAsString())){
            diaryAppService.createDiaryPost(jsonElement);
            return new ApiResult(200, "Diary post successfully created.", PATH);
    }else {
            return new ApiResult(400, "You can not create a post for another user.", PATH);
        }
    }

    // LIST
    // Posts of the user who is logged-in.
    @Override
    @GetMapping("/listDiaryPostFromUser/{username}")
    public ResponseEntity<?> listDiaryPostFromUser(@PathVariable(name = "username") String username) {
        // get logged-in users username and compare it with the username given in the path variable.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(username)){
            return ResponseEntity.ok(diaryAppService.listDiaryPostFromUser(username));
        }else {
            return ResponseEntity.ok("You can not list posts of another user.");
        }
    }

    // DELETE
    // Post of the user who is logged-in.
    // http://localhost:3333/gateway/diary/deleteDiaryPost  ==> DELETE
    // BUG: We do not get the applications response messages. Even though delete is successful the response is still 500 Internal Server Error.
    @Override
    @DeleteMapping("/deleteDiaryPost")
    public ResponseEntity<?> deletePostIfUserIsTheOwner(@RequestBody JsonElement jsonElement) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("deletionrequest_userName").getAsString())){
            diaryAppService.deleteUserPost(jsonElement);
            return ResponseEntity.ok(
                    "If the post ID and the Username you have provided matches in " +
                            "the Diary Application, post deleted. If not, nothing happened.");
        }else {
            return ResponseEntity.ok("You can not delete posts of another user.");
        }
    }
    // UPDATE
    // Post of the user who is logged-in
    // BUG: We do not get the applications response messages. Even though update is successful the response is still 500 Internal Server Error.
    @Override
    @PutMapping("/updateDiaryPost")
    public ResponseEntity<?> updateDiaryPostFromRequest(@RequestBody JsonElement jsonElement) {
        // get logged-in users username and compare it with the post request username.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("request_diaryUpdatedPost_userName").getAsString())){
            diaryAppService.updateDiaryPost(jsonElement);
            return ResponseEntity.ok(
                    "If the post ID and the Username you have provided matches in " +
                            "the Diary Application, post updated. If not, nothing happened.");
        }else {
            return ResponseEntity.ok("You can not update posts of another user.");
        }
    }
}

