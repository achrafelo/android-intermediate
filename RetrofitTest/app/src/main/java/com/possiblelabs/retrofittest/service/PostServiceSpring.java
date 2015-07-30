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
 * Created by possiblelabs on 7/30/15.
 */
public interface PostServiceSpring {

    //CRUDL

    //create
    @POST("/posts")
    void addPost(@Body Post post, Callback<Post> cb);

    //read
    @GET("/posts/{id}")
    void post(@Path("id") String id, Callback<Post> cb);

    //update
    @PUT("/posts/{id}")
    void updatePost(@Path("id") String id, @Body Post post, Callback<Post> cb);

    //update
    @PATCH("/posts/{id}")
    void modifyPost(@Path("id") String id, @Body Post post, Callback<Post> cb);

    //delete
    @DELETE("/posts/{id}")
    void delPost(@Path("id") String id, Callback<Object> cb);

    //list
    @GET("/posts")
    void listPosts(Callback<List<Post>> cb);

    //Filter
    @GET("/posts?order={param}")
    void listPosts(Callback<List<Post>> cb, @Query("param") String param);

}
