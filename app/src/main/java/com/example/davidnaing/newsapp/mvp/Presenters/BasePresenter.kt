package com.example.davidnaing.newsapp.mvp.Presenters

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.davidnaing.newsapp.mvp.Views.BaseView

open class BasePresenter <T : BaseView> : ViewModel(){
    protected lateinit var mView: T
    protected lateinit var mErrorLD: MutableLiveData<String>

    val errorLD: LiveData<String>
        get() = mErrorLD

    open fun initPresenter(mView: T) {
        this.mView = mView
        mErrorLD = MutableLiveData()
    }
}
