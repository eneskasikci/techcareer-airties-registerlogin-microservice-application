package com.example.registerloginapp.apiservices;

import com.google.gson.JsonElement;

import java.util.List;

public interface IBlogAppService {

    // CREATE
    JsonElement createBlogPost(JsonElement jsonElement);

    // DELETE
    JsonElement deletePostIfUserIsTheOwner(JsonElement jsonElement);

    // LIST
    JsonElement getAllBlogPostsFromUser(String username);

    // LIST All Posts
    List<JsonElement> getAllBlogPosts();

    // UPDATE
    JsonElement updateBlogPost(JsonElement jsonElement);
}
