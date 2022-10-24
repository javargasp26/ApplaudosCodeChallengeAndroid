package com.example.applaudoscodechallengeandroid.domain

import com.example.applaudoscodechallengeandroid.data.repository.TvRepository
import javax.inject.Inject

class GetTvListUSeCase @Inject constructor(private val tvRepository: TvRepository) {

    suspend operator fun invoke():List<TvInfo> = tvRepository.getTvList()

}