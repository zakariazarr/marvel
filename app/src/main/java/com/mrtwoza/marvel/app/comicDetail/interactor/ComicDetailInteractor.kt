package com.mrtwoza.marvel.app.comicDetail.interactor

import com.mrtwoza.marvel.app.comicDetail.model.ComicDetailResponse
import com.mrtwoza.marvel.core.base.BaseInteractor


class ComicDetailInteractor() :
    BaseInteractor<getDetailComicRequest, ComicDetailResponse>() {

    override suspend fun doJob(request: getDetailComicRequest): ComicDetailResponse {
        TODO("Not yet implemented")
    }
}


data class getDetailComicRequest (
    val apiKey: String,
    val hash: String,
    val ts: Int
)