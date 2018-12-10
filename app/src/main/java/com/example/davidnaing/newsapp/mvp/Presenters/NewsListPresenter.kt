package com.example.davidnaing.newsapp.mvp.Presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.models.BaseModel
import com.example.davidnaing.newsapp.data.models.NewsModel
import com.example.davidnaing.newsapp.delegate.NewListDelegate
import com.example.davidnaing.newsapp.mvp.Views.NewsListView

class NewsListPresenter() : BasePresenter<NewsListView>(), NewListDelegate {
    override fun onTapNewsItem(imageView: ImageView, newsId: String) {
        mView.navigateToNewsDetailActivity(imageView, newsId = newsId)
    }


    private var mNewsListLD: MutableLiveData<List<NewsVO>>? = null

    override fun initPresenter(mView: NewsListView) {
        super.initPresenter(mView)
        mNewsListLD = MutableLiveData()
    }
    fun onLoadNewsList(countryId : String, categoryID : String, apiKey : String){
        NewsModel.getInstance().startLoadingNewsData(countryID = countryId, categoryID = categoryID,
            apiKey = apiKey,newsListLD = mNewsListLD!!, errorLD = mErrorLD)
    }

    fun onUiReady(): MutableLiveData<ArrayList<NewsVO>>? {
        return NewsModel.getInstance().getNewsListData()
    }
}