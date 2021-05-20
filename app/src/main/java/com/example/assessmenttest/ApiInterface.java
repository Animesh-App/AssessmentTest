package com.example.assessmenttest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL = "https://itunes.apple.com/";

    @GET("search?term=Michael+jackson")
    Call<SongListResponse> getSongsList();
}
