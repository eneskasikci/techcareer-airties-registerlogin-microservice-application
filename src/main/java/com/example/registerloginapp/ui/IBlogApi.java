package com.example.registerloginapp.ui;

import com.example.registerloginapp.errors.ApiResult;
import com.google.gson.JsonElement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBlogApi {

    //SAVE BLOG POST
//    ResponseEntity<?> createBlogPostTo(String blogTitle,
//                                       String blogContent,
//                                       String userName,
//                                       Long blogUserId,
//                                       MultipartFile blogImage) throws IOException;
    //SAVE
    ApiResult savePostToApi(JsonElement jsonElement);

    // DELETE
    ResponseEntity<?> deletePostIfUserIsTheOwner(JsonElement jsonElement);

    // LIST
    ResponseEntity<?> listBlogPostFromUser(String username);

    // LIST ALL POSTS
    ResponseEntity<List<?>> listBlogPosts();

    // UPDATE
    // Post of the user who is logged-in
    ResponseEntity<?> updateBlogPostFromRequest(JsonElement jsonElement);
}
