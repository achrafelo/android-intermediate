package com.possiblelabs.retrofittest.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by possiblelabs on 7/21/15.
 */
public interface PostService {

    //CRUDL

    //list
    @GET("/posts.json")
    void listPosts(Callback<List<Post>> cb);

    //Filter
    @GET("/posts?order={param}.json")
    void listPosts(Callback<List<Post>> cb, @Query("param") String param);

    //read
    @GET("/posts/{id}.json")
    void post(@Path("id") String id, Callback<Post> cb);

    //create
    @POST("/posts.json")
    void addPost(@Body Post post, Callback<Post> cb);

    //update
    @PUT("/posts/{id}.json")
    void updatePost(@Path("id") String id, @Body Post post, Callback<Post> cb);

    //update
    @PATCH("/posts/{id}.json")
    void modifyPost(@Path("id") String id, @Body Post post, Callback<Post> cb);

    //delete
    @DELETE("/posts/{id}.json")
    void delPost(@Path("id") String id, Callback<Object> cb);

}
