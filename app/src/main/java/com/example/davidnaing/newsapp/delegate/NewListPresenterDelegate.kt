package com.example.davidnaing.newsapp.delegate

import com.example.davidnaing.newsapp.mvp.Presenters.NewsListPresenter

interface NewListPresenterDelegate {
    fun getPresenter(): NewsListPresenter
}