package com.example.applaudoscodechallengeandroid.data.network.tv_list

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TvListResponseModel(
    @SerializedName("page") val page: Int = 1,
    @SerializedName("results") val tvs: List<TvInfoResponse>,
) : Serializable

data class TvInfoResponse(
    @SerializedName("id")
    val tvId: Int,
    @SerializedName("name")
    val title: String?,
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("vote_average")
    val rating: String?,
    @SerializedName("overview")
    val overview: String?
) : Serializable
