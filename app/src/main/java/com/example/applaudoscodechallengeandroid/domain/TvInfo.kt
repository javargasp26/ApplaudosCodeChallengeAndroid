package com.example.applaudoscodechallengeandroid.domain

import com.example.applaudoscodechallengeandroid.BuildConfig
import com.example.applaudoscodechallengeandroid.data.local.tv.TvEntity
import com.example.applaudoscodechallengeandroid.data.network.tv_list.TvInfoResponse
import java.io.Serializable

data class TvInfo (
    val tvId: Int,
    val title: String?,
    val image: String?,
    val rating: String?,
    val overview: String?
): Serializable

fun TvInfoResponse.toDomain() = TvInfo(tvId, title, BuildConfig.IMAGE_URL+image, rating, overview)
fun TvEntity.toDomain() = TvInfo(tvId, tvName, image, rating, overview)