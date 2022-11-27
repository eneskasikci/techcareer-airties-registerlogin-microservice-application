package com.example.registerloginapp.retrofit.requests;

import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IBlogServiceRequest {


    // Request is being sent but getting an error from Blog App saying "Current request is not a multipart request"
//    @FormUrlEncoded
//    @POST("/api/blogApp/posts/blogPostWithImage")
//    Call<JsonElement> createBlogPostWithImage(@Field("request_blogTitle") String blogTitle,
//                                              @Field("request_blogContent") String blogContent,
//                                              @Field("request_userName") String userName,
//                                              @Field("request_blogUserId") Long blogUserId,
//                                              @Field("image") MultipartFile blogImage);

//        @Multipart
//        @POST("/api/blogApp/posts/blogPostWithImage")
//        Call<JsonElement> createBlogPostWithImage(@Part("request_blogTitle") String blogTitle,
//                                                @Part("request_blogContent") String blogContent,
//                                                @Part("request_userName") String userName,
//                                                @Part("request_blogUserId") Long blogUserId,
//                                                @Part MultipartFile blogImage);
        @HTTP(method = "POST",path = "/api/blogApp/posts/createBlogPost", hasBody = true)
        Call<JsonElement> createBlogPost(@Body JsonElement jsonElement);

        @GET("/api/blogApp/posts/getAllPostsFromUser/{userName}")
        Call<JsonElement> getAllPostsFromUser(@Path("userName") String userName);

        //DELETE
        @HTTP(method = "DELETE", path = "/api/blogApp/posts/deletePostIfUserIsOwner", hasBody = true)
        Call<JsonElement> deletePostIfUserIsTheOwner(@Body JsonElement jsonElement);

        @PUT("/api/blogApp/posts/updatePostIfUserIsOwner")
        Call<JsonElement> updateBlogPost(@Body JsonElement jsonElement);

        @GET("/api/blogApp/posts/getAllPosts")
        Call<List<JsonElement>> getAllBlogPosts();

        @HTTP(method = "GET",path="/api/blogApp/posts/getImageFromPost/{postId}")
        Call<JsonElement> getImageFromPost(@Path("postId") Long id);

}
//    @POST("/api/blogApp/posts/gatewayBlogPostWithImage")
//    Call<JsonElement> createBlogPostWithImage(@Body JsonElement JsonElement);
