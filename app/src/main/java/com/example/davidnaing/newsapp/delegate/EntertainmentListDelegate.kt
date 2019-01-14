package com.example.davidnaing.newsapp.delegate

import android.widget.ImageView

interface EntertainmentListDelegate {
    fun onTapEntertinmentNewsItem(imageView: ImageView, newsId : String)
    fun onTapEntertainmentNewsRefresh()
    fun onEntertainmentSmartScroll()
}