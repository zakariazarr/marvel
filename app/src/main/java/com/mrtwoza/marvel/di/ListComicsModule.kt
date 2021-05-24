package com.mrtwoza.marvel.di

import com.google.gson.Gson
import com.mrtwoza.marvel.app.listComics.interactor.ListComicsInteractor
import com.mrtwoza.marvel.app.listComics.repository.ListComicsRepository
import com.mrtwoza.marvel.app.listComics.repository.ListComicsRepositoryImpl
import com.mrtwoza.marvel.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ListComicsModule {

    @Singleton
    @Provides
    fun getListComicsRepositoryImpl(apiService: ApiService, gson: Gson): ListComicsRepository {
        return ListComicsRepositoryImpl(apiService,gson)
    }

    @Singleton
    @Provides
    fun getInteractor(repository: ListComicsRepository): ListComicsInteractor{
        return ListComicsInteractor(repository)
    }

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope{
        return CoroutineScope(context =  Dispatchers.Main)
    }
}