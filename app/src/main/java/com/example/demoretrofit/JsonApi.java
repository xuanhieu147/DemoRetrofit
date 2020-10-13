package com.example.demoretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApi {
    @GET("posts")
    Call<List<Example>> getExamples();
}
