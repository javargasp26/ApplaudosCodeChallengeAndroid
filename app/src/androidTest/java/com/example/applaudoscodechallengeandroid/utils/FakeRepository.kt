package com.example.applaudoscodechallengeandroid.utils

import androidx.lifecycle.MutableLiveData
import com.example.applaudoscodechallengeandroid.data.repository.TvRepository
import com.example.applaudoscodechallengeandroid.di.RepositoryModule
import com.example.applaudoscodechallengeandroid.domain.TvInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {

    @Provides
    @Singleton
    fun tvRepository(): TvRepository =
        object : TvRepository {

            private val tvShows = mutableListOf<TvInfo>();
            override suspend fun getTvList(): List<TvInfo> {
                return tvShows
            }

            override suspend fun insertTvList(tvListFromApi: List<TvInfo>) {
                val tvShow = TvInfo(
                    1,
                    "name",
                    "https...",
                    "8",
                    "show overview"
                )
                tvShows.add(tvShow)
            }

        }
}