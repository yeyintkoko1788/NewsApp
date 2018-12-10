package com.example.davidnaing.newsapp.data.models

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.util.Log
import com.example.davidnaing.newsapp.data.VOs.MainVO
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.VOs.SourceVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.lang.RuntimeException
import javax.security.auth.Subject

class NewsModel private constructor(context: Context) : BaseModel(context){
    companion object {
        private var INSTANCE: NewsModel? = null
        fun getInstance(): NewsModel {
            if (INSTANCE == null) {
                throw RuntimeException("News Model is being invoke before initializing")
            }
            val i = INSTANCE
            return i!!
        }

        fun initNewsModel(context: Context){
            INSTANCE = NewsModel(context)
        }
    }

    private var newsListData: MutableLiveData<ArrayList<NewsVO>>? = null
    private var mNewsMap: HashMap<String, NewsVO>? = null

    fun startLoadingNewsData(countryID : String, categoryID : String, apiKey :String,newsListLD: MutableLiveData<List<NewsVO>>,
                             errorLD: MutableLiveData<String>){

        newsListData = MutableLiveData()
        mNewsMap = HashMap()
        val observableObjects = mTheAPI.getNews(countryID = countryID, categoryID = categoryID, apiKey = apiKey)
        observableObjects.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object: io.reactivex.Observer<MainVO>{
                override fun onError(e: Throwable) {
                    Log.e("AA",e.localizedMessage)
                }

                override fun onNext(t: MainVO) {
                    /*var mainVO : MainVO = t
                    newsListData = mainVO.articles*/

                    if (!t.articles!!.isEmpty() && t.articles!!.size >0){
                        newsListLD.value = t.articles!!
                        newsListData!!.value = t.articles!!

                        for (newsResponse in t.articles!!){
                            var sourceVO : SourceVO = newsResponse.source!!
                            mNewsMap!![newsResponse.title!!] = newsResponse
                        }
                    }else{
                        errorLD.value = "Empty List"
                    }
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onComplete() {
                }

            })
    }

    fun getNewsListData(): MutableLiveData<ArrayList<NewsVO>>? {
        return newsListData
    }

    fun getNewsById(newsId : String) : NewsVO{
        return mNewsMap!![newsId]!!
    }

}
