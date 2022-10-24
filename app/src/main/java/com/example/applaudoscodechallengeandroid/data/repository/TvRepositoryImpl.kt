package com.example.applaudoscodechallengeandroid.data.repository

import com.example.applaudoscodechallengeandroid.BuildConfig
import com.example.applaudoscodechallengeandroid.data.local.tv.TvDao
import com.example.applaudoscodechallengeandroid.data.local.tv.toDatabase
import com.example.applaudoscodechallengeandroid.data.network.tv_list.TvListApi
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import com.example.applaudoscodechallengeandroid.domain.toDomain
import javax.inject.Inject

class TvRepositoryImpl @Inject constructor(
    private val tvListApi: TvListApi, private val tvDao: TvDao): TvRepository {

    override suspend fun getTvList():List<TvInfo> {

        tvDao.clearTvTable()
        val tvList = tvDao.getAllTvs().map { it.toDomain() }
        var responseList = emptyList<TvInfo>()

        if (tvList.isEmpty()){
            val listFromApi = tvListApi.getTvList(BuildConfig.API_KEY,1)
            if (listFromApi.isSuccessful){
                if (listFromApi.body()!!.tvs.isNotEmpty()) {
                    responseList = listFromApi.body()!!.tvs.map { it.toDomain() }

                    insertTvList(responseList)
                }
            }
        }else{
            responseList = tvList
        }
        return responseList
    }

    override suspend fun insertTvList(tvListFromApi: List<TvInfo>) {

        tvDao.clearTvTable()
        tvDao.insertAllTv(tvListFromApi.map {it.toDatabase()})

    }
}