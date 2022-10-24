package com.example.applaudoscodechallengeandroid.di

import com.example.applaudoscodechallengeandroid.data.repository.TvRepository
import com.example.applaudoscodechallengeandroid.data.repository.TvRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun tvRepository(repo: TvRepositoryImpl): TvRepository
}