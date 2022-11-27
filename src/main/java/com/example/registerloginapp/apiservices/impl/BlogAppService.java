package com.example.registerloginapp.apiservices.impl;

import com.example.registerloginapp.apiservices.IBlogAppService;
import com.example.registerloginapp.retrofit.RetrofitCommonGenerics;
import com.example.registerloginapp.retrofit.requests.IBlogServiceRequest;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Service
public class BlogAppService implements IBlogAppService {

    // injection
    private final IBlogServiceRequest iBlogServiceRequest;

    @Override
    public JsonElement createBlogPost(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(iBlogServiceRequest.createBlogPost(jsonElement));
    }

    @Override
    public JsonElement getAllBlogPostsFromUser(String username) {
        return RetrofitCommonGenerics.retrofitGenerics(iBlogServiceRequest.getAllPostsFromUser(username));
    }

    @Override
    public List<JsonElement> getAllBlogPosts() {
        return RetrofitCommonGenerics.retrofitGenerics(iBlogServiceRequest.getAllBlogPosts());
    }

    @Override
    public JsonElement deletePostIfUserIsTheOwner(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(iBlogServiceRequest.deletePostIfUserIsTheOwner(jsonElement));
    }

    @Override
    public JsonElement updateBlogPost(JsonElement jsonElement) {
        return RetrofitCommonGenerics.retrofitGenerics(iBlogServiceRequest.updateBlogPost(jsonElement));
    }
}
