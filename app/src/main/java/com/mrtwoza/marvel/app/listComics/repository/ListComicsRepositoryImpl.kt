package com.mrtwoza.marvel.app.listComics.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mrtwoza.marvel.app.listComics.interactor.getListComicsRequest
import com.mrtwoza.marvel.app.listComics.model.ListComicsResponse
import com.mrtwoza.marvel.retrofit.ApiService
import com.mrtwoza.marvel.retrofit.model.ErrorResponse
import java.lang.Exception
import javax.inject.Inject
import kotlin.jvm.Throws

class ListComicsRepositoryImpl @Inject constructor(
    private val service: ApiService,
    private val gson: Gson
) : ListComicsRepository {



    @Throws(Exception::class)
    override fun getListComics(request: getListComicsRequest): ListComicsResponse {
        val response = service.getListComics(
            apikey = request.apiKey,
            hash = request.hash,
            ts = request.ts,
            offset = request.offset,
            limit = request.limit
        ).execute()

        return if (response.isSuccessful){
            response.body()!!
        }else{
            val type = object : TypeToken<ErrorResponse>() {}.type
            var errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
            Log.e("getListComics","error object : $errorResponse")
            throw Exception()
        }
    }

}