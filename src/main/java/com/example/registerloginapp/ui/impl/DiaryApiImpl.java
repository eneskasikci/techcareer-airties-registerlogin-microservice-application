package com.example.registerloginapp.ui.impl;

import com.example.registerloginapp.errors.ApiResult;
import com.example.registerloginapp.apiservices.IDiaryAppService;
import com.example.registerloginapp.security.services.UserDetailsImpl;
import com.example.registerloginapp.ui.IDiaryApi;
import com.google.gson.JsonElement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Api(tags = "Diary Api Implementation", description = "Requests to send to the Diary App")
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
    @ApiOperation(value = "Sends a request to the Diary Application to create a diary post with the logged in user's information.",
            notes = "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Diary Application to create a diary post " +
            "with the logged in user's information. " +
            "It returns a custom message if the diary post is created successfully. " +
            "If you are sending a post request with a different user's information than the logged in user," +
            "It will return a custom error message.")
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
    @ApiOperation(value = "Sends a request to the Diary Application to list all diary posts of the logged in user.",
            notes = "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Diary Application to list all diary posts of the logged in user. " +
            "It returns a list of diary posts if the logged in user has any diary posts. " +
            "If the logged in user gives another username, it returns a custom error message.")
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
    @ApiOperation(value = "Deletes a diary post of the logged in user.",
            notes = "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Diary Application to delete a diary post of the logged in user. " +
            "It returns a custom message if the diary post is deleted successfully. " +
            "If the logged in user gives another username, it returns a custom error message.")
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
    @ApiOperation(value = "Updates a diary post of the logged in user.",
            notes = "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Diary Application to update a diary post of the logged in user. " +
            "It returns a custom message if the diary post is updated successfully. " +
            "If the logged in user gives another username, it returns a custom error message.")
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

