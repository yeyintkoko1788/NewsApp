package com.example.davidnaing.newsapp.mvp.Presenters

import com.example.davidnaing.newsapp.data.models.NewsModel
import com.example.davidnaing.newsapp.mvp.Views.NewsDetailView

/**
 * Created by yepyaesonetun on 10/31/18.
 **/
class NewsDetailPresenter : BasePresenter<NewsDetailView>() {
    override fun initPresenter(mView: NewsDetailView) {
        super.initPresenter(mView)
    }


    fun onUiReady(newsId: String) {
        val newsResponseVO = NewsModel.getInstance().getNewsById(newsId)
        mView.displayNewsDetail(newsResponseVO)
    }

    fun onTabUrl(url : String){
        mView.goToURL(url)
    }
}