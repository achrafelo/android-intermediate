package com.possiblelabs.volleytest.service;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Created by possiblelabs on 7/30/15.
 */
public interface PostCallback {

    void success(Post post);
    void failure(VolleyError ve);
}
