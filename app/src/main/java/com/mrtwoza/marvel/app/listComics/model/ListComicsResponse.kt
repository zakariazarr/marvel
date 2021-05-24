package com.mrtwoza.marvel.app.listComics.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ListComicsResponse (
    @SerializedName("code") @Expose val code : Int,
    @SerializedName("status") @Expose val status : String,
    @SerializedName("copyright") @Expose val copyright : String,
    @SerializedName("attributionText") @Expose val attributionText : String,
    @SerializedName("attributionHTML") @Expose val attributionHTML : String,
    @SerializedName("etag") @Expose val etag : String,
    @SerializedName("data") @Expose val data : DataListComics
)

data class DataListComics (
    @SerializedName("offset") @Expose val offset : Int,
    @SerializedName("limit") @Expose val limit : Int,
    @SerializedName("total") @Expose val total : Int,
    @SerializedName("count") @Expose val count : Int,
    @SerializedName("results") @Expose val results :  List<Comic>
)


data class Comic (
    @SerializedName("id") @Expose val id : Int,
    @SerializedName("digitalId") @Expose val digitalId : Int,
    @SerializedName("title") @Expose val title : String,
    @SerializedName("issueNumber") @Expose val issueNumber : Int,
    @SerializedName("variantDescription") @Expose val variantDescription : String,
    @SerializedName("description") @Expose val description : String,
    @SerializedName("modified") @Expose val modified : String,
    @SerializedName("isbn") @Expose val isbn : String,
    @SerializedName("upc") @Expose val upc : String,
    @SerializedName("diamondCode") @Expose val diamondCode : String,
    @SerializedName("ean") @Expose val ean : String,
    @SerializedName("issn") @Expose val issn : String,
    @SerializedName("format") @Expose val format : String,
    @SerializedName("pageCount") @Expose val pageCount : Int,
    @SerializedName("textObjects") @Expose val textObjects : List<TextObject>,
    @SerializedName("resourceURI") @Expose val resourceURI : String,
    @SerializedName("urls") @Expose val urls : List<Urls>,
    @SerializedName("series") @Expose val series : Series,
    @SerializedName("variants") @Expose val variants : List<Variants>,
    @SerializedName("collections") @Expose val collections : List<Series>,
    @SerializedName("collectedIssues") @Expose val collectedIssues : List<Series>,
    @SerializedName("dates") @Expose val dates : List<Date>,
    @SerializedName("prices") @Expose val prices : List<Prices>,
    @SerializedName("thumbnail") @Expose val thumbnail : Thumbnail,
    @SerializedName("images") @Expose val images : List<Thumbnail>,
    @SerializedName("creators") @Expose val creators : Creators,
    @SerializedName("characters") @Expose val characters : Characters,
    @SerializedName("stories") @Expose val stories : Stories,
    @SerializedName("events") @Expose val events : Events
): Serializable

data class Characters (
    @SerializedName("available") @Expose val available : Int,
    @SerializedName("collectionURI") @Expose val collectionURI : String,
    @SerializedName("items") @Expose val items : List<Series>,
    @SerializedName("returned") @Expose val returned : Int
): Serializable

data class Items (
    @SerializedName("resourceURI") @Expose val resourceURI : String,
    @SerializedName("name") @Expose val name : String,
    @SerializedName("type") @Expose val type : String
): Serializable

data class Date (
    @SerializedName("type") @Expose val type : String,
    @SerializedName("date") @Expose val date : String
): Serializable

data class Prices (

    @SerializedName("type") @Expose val type : String,
    @SerializedName("price") @Expose val price : Double
): Serializable

data class Series (

    @SerializedName("resourceURI") @Expose val resourceURI : String,
    @SerializedName("name") @Expose val name : String
): Serializable

data class Thumbnail (

    @SerializedName("path") @Expose val path : String,
    @SerializedName("extension") @Expose val extension : String
): Serializable

data class Urls (

    @SerializedName("type") @Expose val type : String,
    @SerializedName("url") @Expose val url : String
): Serializable

data class Events (
    @SerializedName("available") @Expose val available : Int,
    @SerializedName("collectionURI") @Expose val collectionURI : String,
    @SerializedName("items") @Expose val items : List<Variants>,
    @SerializedName("returned") @Expose val returned : Int
): Serializable


data class Stories (

    @SerializedName("available") @Expose val available : Int,
    @SerializedName("collectionURI") @Expose val collectionURI : String,
    @SerializedName("items") @Expose val items : List<Items>,
    @SerializedName("returned") @Expose val returned : Int
): Serializable

data class Variants (

    @SerializedName("resourceURI") @Expose val resourceURI : String,
    @SerializedName("name") @Expose val name : String
): Serializable

data class Creators (

    @SerializedName("available") @Expose val available : Int,
    @SerializedName("collectionURI") @Expose val collectionURI : String,
    @SerializedName("items") @Expose val items : List<Items>,
    @SerializedName("returned") @Expose val returned : Int
): Serializable

data class TextObject (

    @SerializedName("type") @Expose val type : String,
    @SerializedName("language") @Expose val language : String,
    @SerializedName("text") @Expose val text : String
): Serializable