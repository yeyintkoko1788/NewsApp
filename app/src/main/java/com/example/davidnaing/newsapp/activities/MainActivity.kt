package com.example.davidnaing.newsapp.activities

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.davidnaing.newsapp.R
import com.example.davidnaing.newsapp.adapters.NewsRVAdapter
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.models.NewsModel
import com.example.davidnaing.newsapp.mvp.Presenters.NewsListPresenter
import com.example.davidnaing.newsapp.mvp.Views.NewsListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsListView {
    override fun navigateToNewsDetailActivity(imageView: ImageView, newsId: String) {
        val intent = NewsDetailActivity.getIntent(this, newsId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair(imageView, getString(R.string.detail_transition_name))
            )
            ActivityCompat.startActivity(this, intent, activityOptions.toBundle())
        } else {
            startActivity(intent)
        }//
    }

    override fun displayList(newsList: List<NewsVO>) {
        mAdapter.setNewData(newsList as MutableList<NewsVO>)
    }

    private lateinit var mAdapter: NewsRVAdapter
    private lateinit var mPresenter: NewsListPresenter

    override fun displayNewsListData(customVO: List<NewsVO>) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
       //supportActionBar!!.title = "Business News"

       /* btn_business.focusable
        btn_business.isFocusableInTouchMode
        btn_business.requestFocus()

        var x = btn_business.left
        var y = btn_business.top
        hsv.scrollTo(x,y)
        btn_business.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                btn_business.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }*/
        NewsModel.initNewsModel(context = this)
        mPresenter = ViewModelProviders.of(this)
            .get(NewsListPresenter::class.java)
        mPresenter.initPresenter(this)

        mAdapter = NewsRVAdapter(this, mPresenter)

        rv_main.setHasFixedSize(true)
        rv_main.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_main.adapter = mAdapter

        mPresenter.onLoadNewsList("us","business", "8a7ee59af74c4085addcceb90269a6bb")
        observeNewsList()
        toolbar.setNavigationOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                finish()
            }

        })

    }
    private fun observeNewsList() {
        mPresenter.onUiReady()!!.observe(this, Observer<ArrayList<NewsVO>> { newsList: ArrayList<NewsVO>? ->
            run {
                displayList(newsList!!)
            }
        })
    }

}
