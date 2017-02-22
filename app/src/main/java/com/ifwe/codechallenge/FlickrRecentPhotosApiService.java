package com.ifwe.codechallenge;

import com.ifwe.codechallenge.models.Details;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sirig on 2/21/17.
 */

public interface FlickrRecentPhotosApiService {

    @GET("/services/rest/?method=flickr.photos.getRecent")
    public Call<Details> getRecentPhotos(@Query("per_page") int perPage, @Query("extras") String
            extras);


}
