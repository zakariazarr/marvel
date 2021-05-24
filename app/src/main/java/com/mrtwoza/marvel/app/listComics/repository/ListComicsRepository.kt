package com.mrtwoza.marvel.app.listComics.repository

import com.mrtwoza.marvel.app.listComics.interactor.getListComicsRequest
import com.mrtwoza.marvel.app.listComics.model.ListComicsResponse
import java.lang.Exception
import kotlin.jvm.Throws


interface ListComicsRepository {

    @Throws(Exception::class)
    fun getListComics(request: getListComicsRequest): ListComicsResponse
}