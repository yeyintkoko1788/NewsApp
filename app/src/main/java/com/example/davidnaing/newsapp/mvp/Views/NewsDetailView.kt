package com.example.davidnaing.newsapp.mvp.Views

import com.example.davidnaing.newsapp.data.VOs.NewsVO

/**
 * Created by yepyaesonetun on 10/31/18.
 **/
interface NewsDetailView : BaseView {
    fun displayNewsDetail(newsVO: NewsVO)
    fun goToURL(url : String)
}