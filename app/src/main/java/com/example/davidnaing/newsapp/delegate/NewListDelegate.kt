package com.example.davidnaing.newsapp.delegate

import android.widget.ImageView
import com.example.davidnaing.newsapp.data.VOs.NewsVO

interface NewListDelegate {
    fun onTapNewsItem(imageView: ImageView, newsId : String)
}