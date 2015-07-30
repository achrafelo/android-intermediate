package com.possiblelabs.volleytest.service;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by possiblelabs on 7/30/15.
 */
public interface PostsCallback {

    public void success(List<Post> posts);

    public void failure(VolleyError ve);
}
