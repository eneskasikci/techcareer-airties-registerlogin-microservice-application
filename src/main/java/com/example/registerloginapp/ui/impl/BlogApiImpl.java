package com.example.registerloginapp.ui.impl;

import com.example.registerloginapp.ui.IBlogApi;

import com.example.registerloginapp.apiservices.IBlogAppService;
import com.example.registerloginapp.errors.ApiResult;
import com.example.registerloginapp.retrofit.RetrofitCommonGenerics;
import com.example.registerloginapp.security.services.UserDetailsImpl;
import com.example.registerloginapp.ui.IBlogApi;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("gateway/blog")
public class BlogApiImpl implements IBlogApi {
    private final IBlogAppService blogAppService;

    private static final String PATH = "gateway/blog";

    // CREATE
    // Diary post of the user who is logged-in.
    // http://localhost:3333/gateway/blog/createBlogPost  ==> POST
//    @Override
//    @PostMapping("/createBlogPost")
//    public ResponseEntity<?> createBlogPostTo(@RequestParam(name = "request_blogTitle") String blogTitle,
//                                                 @RequestParam(name = "request_blogContent") String blogContent,
//                                                 @RequestParam(name = "request_userName") String userName,
//                                                 @RequestParam(name = "request_blogUserId") Long blogUserId,
//                                                 @RequestParam(name = "image") MultipartFile blogImage) throws IOException {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(userDetails.getUsername().equals(userName)){
//            byte[] imageBytes = blogImage.getBytes();
//            blogAppService.blogPostWithImage(RetrofitCommonGenerics.convertStringToJson(blogTitle, blogContent, userName, blogUserId, imageBytes));
//            return ResponseEntity.ok(new ApiResult(200, "Blog post successfully created.", PATH));
//        }else {
//            return ResponseEntity.ok(new ApiResult(400, "You can not create a post for another user.", PATH));
//        }
//    }

    @Override
    @PostMapping("/createBlogPost")
    public ApiResult savePostToApi(JsonElement jsonElement) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePostIfUserIsTheOwner(JsonElement jsonElement) {
        return null;
    }

    @Override
    public ResponseEntity<?> listBlogPostFromUser(String username) {
        return null;
    }

    @Override
    @GetMapping("/listBlogPosts")
    public ResponseEntity<List<?>> listBlogPosts() {
        return ResponseEntity.ok(blogAppService.getAllBlogPosts());
    }

    @Override
    public ResponseEntity<?> updateBlogPostFromRequest(JsonElement jsonElement) {
        return null;
    }


}
