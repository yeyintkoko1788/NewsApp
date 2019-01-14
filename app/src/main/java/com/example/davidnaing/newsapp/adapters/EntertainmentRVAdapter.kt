package com.example.davidnaing.newsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.davidnaing.newsapp.R
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.delegate.NewListDelegate
import com.example.davidnaing.newsapp.viewholders.BaseViewHolder
import com.example.davidnaing.newsapp.viewholders.NewItemViewHolder

class EntertainmentRVAdapter (context : Context, private var delegate : NewListDelegate) : BasedRecyclerAdapter<NewItemViewHolder, NewsVO>(context){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder<NewsVO> {
        return NewItemViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.news_list, p0, false), delegate)
    }


}