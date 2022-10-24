package com.example.applaudoscodechallengeandroid.data.network.tv_list

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvListApi {
    @GET("tv/popular")
    suspend fun getTvList(@Query("api_key") apiKey: String,@Query("page") page: Int): Response<TvListResponseModel>
}