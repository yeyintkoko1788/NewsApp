package com.example.davidnaing.newsapp.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.delegate.NewListDelegate
import kotlinx.android.synthetic.main.news_list.view.*


class NewItemViewHolder(itemView : View, private val delegate: NewListDelegate) :BaseViewHolder<NewsVO>(itemView) {
    override fun onClick(v: View?) {
    }

    override fun setData(data: NewsVO) {
        itemView.tv_title.text = data.title
        itemView.tv_author.text = data.author

        Glide.with(itemView.article_img.context)
            .load(data.urlToImage)
            .into(itemView.article_img)
        itemView.setOnClickListener { delegate.onTapNewsItem(itemView.article_img, data.title!!) }
    }

}