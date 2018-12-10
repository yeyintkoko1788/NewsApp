package com.example.davidnaing.newsapp.data.VOs

import com.google.gson.annotations.SerializedName

class NewsVO {
    @SerializedName("source")
    var source : SourceVO? = null

    @SerializedName("author")
    var author : String? = null

    @SerializedName("title")
    var title : String? = null

    @SerializedName("description")
    var description : String? = null

    @SerializedName("url")
    var url : String? = null

    @SerializedName("urlToImage")
    var urlToImage : String? = null

    @SerializedName("publishedAt")
    var publishedAt : String? = null

    @SerializedName("content")
    var cont : String? = null
}