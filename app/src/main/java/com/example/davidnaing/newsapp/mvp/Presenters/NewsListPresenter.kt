package com.example.davidnaing.newsapp.mvp.Presenters

import android.arch.lifecycle.MutableLiveData
import android.widget.ImageView
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.models.NewsModel
import com.example.davidnaing.newsapp.delegate.EntertainmentListDelegate
import com.example.davidnaing.newsapp.delegate.NewListDelegate
import com.example.davidnaing.newsapp.mvp.Views.NewsListView

class NewsListPresenter : BasePresenter<NewsListView>(), NewListDelegate, EntertainmentListDelegate {
    override fun onEntertainmentSmartScroll() {
        mView.onEntertainmentSmartScroll()
    }

    override fun onTapEntertainmentNewsRefresh() {
        mView.onTapEntertainmentRefreshEmptyView()
    }

    override fun onTapEntertinmentNewsItem(imageView: ImageView, newsId: String) {
        mView.navigateToNewsDetailActivity(imageView,newsId)
    }

    override fun onSmartScroll() {
        mView.onSmartScroll()
    }

    override fun onTapBusinessNewsRefresh() {
        mView.onTapBusinessRefreshEmptyView()
    }

    override fun onTapNewsItem(imageView: ImageView, newsId: String) {
        mView.navigateToNewsDetailActivity(imageView, newsId = newsId)
    }


    private var mNewsListLD: MutableLiveData<List<NewsVO>>? = null
    private var mEntertainmentListLD: MutableLiveData<List<NewsVO>>? = null

    override fun initPresenter(mView: NewsListView) {
        super.initPresenter(mView)
        mNewsListLD = MutableLiveData()
        mEntertainmentListLD = MutableLiveData()
    }
    fun onLoadBusinessNewsList(countryId : String, categoryID : String, apiKey : String,page : Int){
        NewsModel.getInstance().startLoadingBusinessNewsData(countryID = countryId, categoryID = categoryID,
            apiKey = apiKey,page = page,newsListLD = mNewsListLD!!, errorLD = mErrorLD)
    }

    fun onLoadEntertainmentList(countryId: String,categoryID: String, apiKey: String, page: Int){
        NewsModel.getInstance().startLoadingBusinessNewsData(countryID = countryId, categoryID = categoryID,
            apiKey = apiKey,page = page,newsListLD = mEntertainmentListLD!!, errorLD = mErrorLD)
    }

    fun onLoadMore(countryId: String,categoryID: String, apiKey: String, page: Int){
        NewsModel.getInstance().startLoadingBusinessNewsData(countryID = countryId, categoryID = categoryID,
            apiKey = apiKey,page = page,newsListLD = mNewsListLD!!, errorLD = mErrorLD)
    }

    fun onUiReady(): MutableLiveData<ArrayList<NewsVO>>? {
        return NewsModel.getInstance().getNewsListData()
    }

    val mBusinessLD : MutableLiveData<List<NewsVO>>
        get() = mNewsListLD!!

    val mEntertainmentLD : MutableLiveData<List<NewsVO>>
        get() = mEntertainmentListLD!!
}