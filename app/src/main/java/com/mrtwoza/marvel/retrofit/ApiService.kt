package com.mrtwoza.marvel.retrofit

import com.mrtwoza.marvel.app.listComics.model.ListComicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/public/comics")
    fun getListComics(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<ListComicsResponse>


    /*@GET("CandidateAvailabilities/{id}")
    fun getAvailabilities(
        @Path("id") candidateId: String?,
        @Query("pStartDate") startDate: DateTime?,
        @Query("pEndDate") endDate: DateTime?
    ): Call<RestAvailabilities?>?*/
}