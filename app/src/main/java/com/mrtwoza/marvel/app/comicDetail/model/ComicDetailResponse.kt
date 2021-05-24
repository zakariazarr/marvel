package com.mrtwoza.marvel.app.comicDetail.model

// To parse the JSON, install Klaxon and do:
//
//   val welcome2 = Welcome2.fromJson(jsonString)


import com.beust.klaxon.*


private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Title::class, { Title.fromValue(it.string!!) }, { "\"${it.value}\"" })


data class ComicDetailResponse (
    val code: Long,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val etag: String,
    val data: Data
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<ComicDetailResponse>(json)
    }
}

data class Data (
    val offset: Long,
    val limit: Long,
    val total: Long,
    val count: Long,
    val results: List<Result>
)

data class Result (
    val id: Long,

    @Json(name = "digitalId")
    val digitalID: Long,

    val title: Title,
    val issueNumber: Long,
    val variantDescription: String,
    val description: Any? = null,
    val modified: String,
    val isbn: String,
    val upc: String,
    val diamondCode: String,
    val ean: String,
    val issn: String,
    val format: String,
    val pageCount: Long,
    val textObjects: List<Any?>,
    val resourceURI: String,
    val urls: List<URL>,
    val series: Series,
    val variants: List<Series>,
    val collections: List<Any?>,
    val collectedIssues: List<Any?>,
    val dates: List<Date>,
    val prices: List<Price>,
    val thumbnail: Thumbnail,
    val images: List<Any?>,
    val creators: Characters,
    val characters: Characters,
    val stories: Characters,
    val events: Characters
)

data class Characters (
    val available: Long,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Long
)

data class Item (
    val resourceURI: String,
    val name: String,
    val role: String? = null,
    val type: String? = null
)

data class Date (
    val type: String,
    val date: String
)

data class Price (
    val type: String,
    val price: Long
)

data class Series (
    val resourceURI: String,
    val name: Title
)

enum class Title(val value: String) {
    MarvelPreviews2017("Marvel Previews (2017)"),
    MarvelPreviews2017Present("Marvel Previews (2017 - Present)");

    companion object {
        public fun fromValue(value: String): Title = when (value) {
            "Marvel Previews (2017)"           -> MarvelPreviews2017
            "Marvel Previews (2017 - Present)" -> MarvelPreviews2017Present
            else                               -> throw IllegalArgumentException()
        }
    }
}

data class Thumbnail (
    val path: String,
    val extension: String
)

data class URL (
    val type: String,
    val url: String
)
