package com.example.davidnaing.newsapp.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.customtabs.*
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.davidnaing.newsapp.R
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.VOs.SourceVO
import com.example.davidnaing.newsapp.mvp.Presenters.NewsDetailPresenter
import com.example.davidnaing.newsapp.mvp.Views.NewsDetailView
import kotlinx.android.synthetic.main.news_detail_activity.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewsDetailActivity :BaseActivity(), NewsDetailView{
    override fun goToURL(url: String) {

        val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
            .setToolbarColor(Color.parseColor("#212F3C"))
            .setShowTitle(true)
            .build()

        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    override fun displayNewsDetail(newsVO: NewsVO?) {
        if(newsVO != null){
            var d: Date? = null
            try
            {
                d = sdf.parse(newsVO.publishedAt)
            }
            catch (e: ParseException)
            {
                e.printStackTrace()
            }
            var sourceVO :SourceVO = newsVO.source!!
            val formattedTime = output.format(d)
            tv_author.text = newsVO.author
            tv_content.text = newsVO.cont
            tv_date.text = formattedTime
            tv_title.text = newsVO.title
            tv_source.text = sourceVO.name
            url = newsVO.url
            Glide.with(article_img)
                .load(newsVO.urlToImage)
                .into(article_img)
        }else{
            Toast.makeText(this,"News Detail Not Available",Toast.LENGTH_SHORT).show()
        }

    }

    override fun setUpContents(savedInstanceState: Bundle?) {
    }

    override fun getlayoutRes(): Int {
        return R.layout.news_detail_activity
    }

    companion object {
        private const val IE_NEWS_ID = "IE_NEWS_ID"

        fun getIntent(context: Context, newsId: String): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(IE_NEWS_ID, newsId)
            return intent
        }
    }

    private lateinit var mPresenter: NewsDetailPresenter
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val output = SimpleDateFormat("d MMM yyyy,hh:mm aaa")
    private var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient? = null
    private var mCustomTabsSession: CustomTabsSession? = null
    private var url : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail_activity)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        mPresenter = ViewModelProviders.of(this)
            .get(NewsDetailPresenter::class.java)
        mPresenter.initPresenter(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            article_img.transitionName = getString(R.string.detail_transition_name)
        }

        val newsId :String = intent.getStringExtra(IE_NEWS_ID)
        mPresenter.onUiReady(newsId)
        tv_link.setOnClickListener{mPresenter.onTabUrl(url!!)}

        mCustomTabsServiceConnection = object : CustomTabsServiceConnection(){
            override fun onServiceDisconnected(name: ComponentName?) {
                mClient = null
            }

            override fun onCustomTabsServiceConnected(p0: ComponentName?, p1: CustomTabsClient?) {
                mClient = p1
                mClient?.warmup(0L)
                mCustomTabsSession = mClient?.newSession(null)
            }

        }
    }
}

