package com.example.davidnaing.newsapp.mvp.Views

import android.widget.ImageView
import com.example.davidnaing.newsapp.data.VOs.NewsVO

interface NewsListView : BaseView{
    fun onTapBusinessRefreshEmptyView()
    fun displayNewList(newsList: List<NewsVO>?)
    fun displayEntertainmentList(newsList: List<NewsVO>?)
    fun displayNewEntertainmentList(newsList: List<NewsVO>?)
    fun displayNewsListData(customVO: List<NewsVO>)
    fun displayList(newsList: List<NewsVO>?)
    fun navigateToNewsDetailActivity(imageView: ImageView, newsId : String)
    fun onSmartScroll()
    fun onEntertainmentSmartScroll()
    fun onTapEntertainmentRefreshEmptyView()
}