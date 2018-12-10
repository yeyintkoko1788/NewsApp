package com.example.davidnaing.newsapp.data.VOs

import android.arch.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName

class MainVO {
    @SerializedName("status")
    var status : String? = null

    @SerializedName("totalResults")
    var totalResult : Int? = null

    @SerializedName("articles")
    var articles : ArrayList<NewsVO>? = null

}