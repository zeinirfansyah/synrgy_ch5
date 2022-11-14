package com.zen.androidapinetworking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

//    @GET("movie/now_playing")
//    fun getMovieNowPlaying(@Query ("api_key") apiKey: String)
//
//    @POST("movie/{movie_id}/rating")
//    fun postRateMovie(@Path("movie_id") movie_id:String)
//
//    @POST("customer/auth/login")
//    fun login(@Body requestLogin: RequestLogin)

    @GET("movie/popular")
    fun getMoviePopular(@Query ("api_key") apiKey: String): Call<MoviePopular>

}