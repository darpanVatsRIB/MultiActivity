package com.example.swarmapp;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface swarmApi {


    //....Relative URL...//

    @GET("template")
    Call<List<Post>> getPost(
            @Query("userId") Integer ID,
            @Query("Name") String name,
            @Query("Desc") String desc
    );

    @GET()
    Call<String> getStringResponse(@Url String url);

    @GET("posts")
    Call<List<Post>> getPost(@QueryMap Map<String, String> parameters);

    @GET("posts")
    Call<List<Post>> getPosts(@Url String url);


    @GET("posts/2/comments")
    Call<List<Comment>> getCommments();

    @GET
    Call<List<Comment>> getComment(@Url String url);

    @POST("template")
    Call<Post>createPost(@Body Post post);


    @FormUrlEncoded
    @POST("template")
    Call<Post> createPost(
            @Field("name") String name,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("template")
    Call<Post> createPost(@FieldMap Map<String, String> fields);



}
