package com.possiblelabs.retrofittest.service;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by possiblelabs on 7/21/15.
 */
public class PostHelper {

    //RAILS LOCAL APPLICATION
    private static final String URL = "http://10.0.0.10:3000/";
    private static PostService service;

    public static PostService getService() {
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
            service = restAdapter.create(PostService.class);
        }

        return service;
    }
}
