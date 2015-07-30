package com.possiblelabs.volleytest.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by possiblelabs on 7/30/15.
 */
public class PostHelper {

    //Working with ServerSpringTest
    private static final String URL = "http://10.0.0.10:8080/";
    private Gson gson;
    private RequestQueue queue;

    public PostHelper(Context context) {
        gson = new Gson();
        queue = Volley.newRequestQueue(context);
    }

    public void listPosts(final PostsCallback pc) {

        JsonArrayRequest jsObjRequest = new JsonArrayRequest(Request.Method.GET, URL + "posts", "",
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Type listType = new TypeToken<List<Post>>() {}.getType();
                        List<Post> posts = gson.fromJson(response.toString(), listType);
                        pc.success(posts);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }

    public void getPost(final String id, final PostCallback pc) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL + "posts/" + id, "",
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = gson.fromJson(response.toString(), Post.class);
                        pc.success(post);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }

    public void addPost(Post post, final PostCallback pc) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, URL + "posts", gson.toJson(post),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = gson.fromJson(response.toString(), Post.class);
                        pc.success(post);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }

    public void updatePost(final String id, Post post, final PostCallback pc) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.PUT, URL + "posts/" + id, gson.toJson(post),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = gson.fromJson(response.toString(), Post.class);
                        pc.success(post);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }


    //com.android.volley.NoConnectionError: java.net.ProtocolException: Unknown method 'PATCH'; must be one of [OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE]
    public void modifyPost(final String id, Post post, final PostCallback pc) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.PATCH, URL + "posts/" + id, gson.toJson(post),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = gson.fromJson(response.toString(), Post.class);
                        pc.success(post);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }

    public void delPost(final String id, final DelPostCallback pc) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.DELETE, URL + "posts/" + id, "",
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Post post = gson.fromJson(response.toString(), Post.class);
                        pc.success(post);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pc.failure(error);
                    }
                });

        queue.add(jsObjRequest);
    }
}
