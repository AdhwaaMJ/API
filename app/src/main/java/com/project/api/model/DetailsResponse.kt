package com.project.api.model

import android.provider.MediaStore.Audio.Genres
import com.google.gson.annotations.SerializedName

data class DetailsResponse(

    @SerializedName ("adult") var adult: Boolean? = false,
    @SerializedName ("backdrop_path") var backdrop_path: String? = null,
    @SerializedName ("belongs_to_collection") var belongs_to_collection: String? = null,
    @SerializedName ("budget") var budget: Double? = null,
    @SerializedName ("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerializedName ("homepage") var homepage: String? = null,
    @SerializedName ("id") var id: Int? = null,
    @SerializedName ("imdb_id") var imdb_id: String? = null,
    @SerializedName ("original_language") var original_language: String? = null,
    @SerializedName ("original_title") var original_title: String? = null,
    @SerializedName ("overview") var overview: String? = null,
    @SerializedName ("popularity") var popularity: Double? = null,
    @SerializedName ("poster_path") var poster_path: String? = null,
    @SerializedName ("production_companies") var production_companies: ArrayList<Int> = arrayListOf(),
    @SerializedName("production_countries") var production_countries: ArrayList<Int> = arrayListOf(),
    @SerializedName("release_date") var release_date: String? = null,
    @SerializedName("revenue") var revenue: Double? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("spoken_languages") var spoken_languages: ArrayList<Int> = arrayListOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = false,
    @SerializedName("vote_average") var vote_average: Double? = null,
    @SerializedName("vote_count") var vote_count: Int? = null,

)

data class genres(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name")  var name: String? = null

)

data class production_companies(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("logo_path") var logo_path: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("origin_country") var origin_country: String? = null,
)

data class production_countries(
    @SerializedName("iso_3166_1") var iso_3166_1: String? = null,
    @SerializedName("name") var name: String? = null,

    )

data class spoken_languages(
    @SerializedName("english_name") var english_name: String? = null,
    @SerializedName("iso_639_1") var iso_639_1: String? = null,
    @SerializedName("name") var name: String? = null,

    )


