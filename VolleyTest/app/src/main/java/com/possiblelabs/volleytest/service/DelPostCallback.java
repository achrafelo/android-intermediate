package com.possiblelabs.volleytest.service;

import com.android.volley.VolleyError;

/**
 * Created by possiblelabs on 7/30/15.
 */
public interface DelPostCallback {

    void success(Post post);
    void failure(VolleyError ve);
}
