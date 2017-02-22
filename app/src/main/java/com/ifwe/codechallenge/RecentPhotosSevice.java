package com.ifwe.codechallenge;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by sirig on 2/21/17.
 */

public class RecentPhotosSevice {

    private static final String SERVER_URL = "https://api.flickr.com";
    private static final String API_KEY = "af4f9c09c5ac505ceb14d30232360f9b";
    private static final String TAG = "FlickrFetchrService";


    public Retrofit getRestAdapter() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(logging);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .addQueryParameter("format", "json")
                        .addQueryParameter("nojsoncallback", "1")
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
        return retrofit;
    }
}
