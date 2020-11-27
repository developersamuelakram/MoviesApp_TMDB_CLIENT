package com.example.moviesapp.Service;

import com.example.moviesapp.Model.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMoviesService {



    @GET("movie/popular")
    Call<Info> getMovies(@Query("api_key")String api_key);
}
