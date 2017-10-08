package com.knz21.zipcode.webapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ZipCodeApi {

    @GET("search")
    Call<SearchResponse> search(@Query("zipcode") String zipcode);
}
