package com.example.applaudoscodechallengeandroid.data.repository

import com.example.applaudoscodechallengeandroid.domain.TvInfo

interface TvRepository {
    suspend fun getTvList():List<TvInfo>

    suspend fun insertTvList(tvListFromApi: List<TvInfo>)
}
