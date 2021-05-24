package com.mrtwoza.marvel.app.listComics.interactor

import com.mrtwoza.marvel.app.listComics.model.ListComicsResponse
import com.mrtwoza.marvel.app.listComics.repository.ListComicsRepository
import com.mrtwoza.marvel.core.base.BaseInteractor
import javax.inject.Inject


class ListComicsInteractor @Inject constructor(
    private val repository: ListComicsRepository
) : BaseInteractor<getListComicsRequest, ListComicsResponse>()
{
    override suspend fun doJob(request: getListComicsRequest): ListComicsResponse {
        return repository.getListComics(request)
    }
}


data class getListComicsRequest(
    val apiKey: String,
    val hash: String,
    val ts: Int,
    val limit: Int,
    val offset: Int
)