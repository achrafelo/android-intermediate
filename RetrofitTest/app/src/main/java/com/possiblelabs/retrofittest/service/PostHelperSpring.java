package com.possiblelabs.retrofittest.service;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by possiblelabs on 7/21/15.
 */
public class PostHelperSpring {

    //LOCAL SERVICE SPRING
    private static final String URL = "http://10.0.0.10:8080/";
    private static PostServiceSpring service;

    public static PostServiceSpring getService() {
        if (service == null) {
            RequestInterceptor interceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("User-Agent", System.getProperty("http.agent"));
                }
            };

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(URL)
                    .setClient(new OkClient())
                    .setRequestInterceptor(interceptor)
                    .build();
            service = restAdapter.create(PostServiceSpring.class);
        }

        return service;
    }
}
