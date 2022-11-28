package com.example.registerloginapp.ui.impl;

import com.example.registerloginapp.security.services.UserDetailsImpl;
import com.example.registerloginapp.ui.IBlogApi;

import com.example.registerloginapp.apiservices.IBlogAppService;
import com.example.registerloginapp.errors.ApiResult;
import com.google.gson.JsonElement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Api(tags = "Blog Api Implementation", description = "Requests to send to the Blog App")
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
    @ApiOperation(value = "Create a blog post", notes = "" +
            "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Blog Application to create a blog post" +
            "With the logged in user's information." +
            "It returns a custom message if the blog post is created successfully." +
            "If you are sending a post request with a different user's information than the logged in user," +
            "It will return a custom error message." +
            "We are not able send Image with this request.")
    @PostMapping("/createBlogPost")
    public ResponseEntity<?> savePostToApi(@RequestBody JsonElement jsonElement) {
        // get logged-in users username and compare it with the post request username.
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("request_userName").getAsString())){
            blogAppService.createBlogPost(jsonElement);
            return ResponseEntity.ok("Diary post successfully created.");
        }else {
            return ResponseEntity.ok("You can not create a post for another user.");
        }
    }

    @Override
    public ResponseEntity<?> deletePostIfUserIsTheOwner(JsonElement jsonElement) {
        return null;
    }

    @Override
    @ApiOperation(value = "Get all blog posts", notes = "" +
            "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Blog Application to get all blog posts." +
            "If it is successful, it returns a list of blog posts.")
    @GetMapping("/listBlogPosts")
    public ResponseEntity<List<?>> listBlogPosts() {
        return ResponseEntity.ok(blogAppService.getAllBlogPosts());
    }

//
//    @Override
//    @DeleteMapping("/deleteBlogPost")
//    public ResponseEntity<?> deletePostIfUserIsTheOwner(JsonElement jsonElement) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(userDetails.getUsername().equals(jsonElement.getAsJsonObject().get("deletionrequest_userName").getAsString())){
//            blogAppService.deletePostIfUserIsTheOwner(jsonElement);
//            return ResponseEntity.ok(
//                    "If the post ID and the Username you have provided matches in " +
//                            "the Diary Application, post deleted. If not, nothing happened.");
//        }else {
//            return ResponseEntity.ok("You can not delete posts of another user.");
//        }
//    }

    @Override
    @ApiOperation(value = "To check the photo attached to a blog post.",
            notes = "This call doesn't work. GSON gives an error." +
            "This needs a valid JWT token with the logged in user." +
            "It sends a request to the Blog Application to get the photo attached to a blog post." +
            "If it is successful, it returns the photo attached to a blog post.")
    @GetMapping("/seePhoto/{id}")
    public ResponseEntity<?> seePhoto(@PathVariable Long id) {
        return ResponseEntity.ok(blogAppService.getPhoto(id));
    }

    @Override
    public ResponseEntity<?> listBlogPostFromUser(String username) {
        return null;
    }


    @Override
    public ResponseEntity<?> updateBlogPostFromRequest(JsonElement jsonElement) {
        return null;
    }


}
