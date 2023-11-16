package com.example.movieapp.api

import com.example.movieapp.model.ResponseApi
import com.example.movieapp.model.movieResponse.MoviesResponse
import com.example.movieapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @Headers(
        "accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YTY5ZTY3YjhhNzZiNzNlNzc1ZjE4M2NjMWM0ZWY1MyIsInN1YiI6IjYwYWU0NGM0YmE0ODAyMDAyOTI3ZGI2MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RztaA99qPdw_mti9s3ILMRLklYVIVVHblME_KJsXDoY"
    )
    @GET
    suspend fun getPopularMovies(
        @Url url: String,
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>


    @GET(Constants.END_POINT)
    suspend fun getAnimations(
        @Query("page") page: Int
    ): Response<ResponseApi>
}