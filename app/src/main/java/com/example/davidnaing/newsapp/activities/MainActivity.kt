package com.example.davidnaing.newsapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.widget.ImageView
import com.example.davidnaing.newsapp.R
import com.example.davidnaing.newsapp.adapters.NewsRVAdapter
import com.example.davidnaing.newsapp.components.SmartScrollListener
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.data.models.NewsModel
import com.example.davidnaing.newsapp.delegate.NewListPresenterDelegate
import com.example.davidnaing.newsapp.fragment.BusinessFragment
import com.example.davidnaing.newsapp.fragment.EntertainmentFragment
import com.example.davidnaing.newsapp.mvp.Presenters.NewsListPresenter
import com.example.davidnaing.newsapp.mvp.Views.NewsListView
import com.example.davidnaing.newsapp.utils.AppConstants
import com.yammobots.paradiso.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsListView, NewListPresenterDelegate {
    override fun displayNewEntertainmentList(newsList: List<NewsVO>?) {
        var EntertainmentFragment : EntertainmentFragment = pagerAdapter.getItem(1)as EntertainmentFragment
        EntertainmentFragment.addNewEntertainmentNewsToAdapter(newsList)
    }

    override fun displayEntertainmentList(newsList: List<NewsVO>?) {
        var EntertainmentFragment : EntertainmentFragment = pagerAdapter.getItem(1) as EntertainmentFragment
        EntertainmentFragment.addEntertainmentNewsToAdapter(newsList)
    }

    override fun onTapEntertainmentRefreshEmptyView() {
        mPresenter.onLoadEntertainmentList("us","entertainment","4f706c8d115f445bbf44cc1e301175ea",1)
        observeEntertainmentList()
    }

    override fun onEntertainmentSmartScroll() {
        mPresenter.onLoadEntertainmentList("us","entertainment","4f706c8d115f445bbf44cc1e301175ea",++mPageNumber)
        observeEntertainmentListSmartScroll()
    }

    override fun displayNewList(newsList: List<NewsVO>?) {
        val businessFragment : BusinessFragment = pagerAdapter.getItem(0) as BusinessFragment
        businessFragment.addNewBusinessNewsToAdapter(newsList)
    }

    override fun onSmartScroll() {
            mPresenter.onLoadMore("us","business","4f706c8d115f445bbf44cc1e301175ea",++mPageNumber)
            observeNewsListSmartScroll()
    }

    override fun getPresenter(): NewsListPresenter {
        return mPresenter
    }

    override fun onTapBusinessRefreshEmptyView() {
        mPresenter.onLoadBusinessNewsList("us","business","4f706c8d115f445bbf44cc1e301175ea",1)
        observeNewsList()

    }

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

    override fun displayList(newsList: List<NewsVO>?) {
        //mPresenter.onLoadBusinessNewsList("us","business","4f706c8d115f445bbf44cc1e301175ea")
        val businessFragment : BusinessFragment = pagerAdapter.getItem(0) as BusinessFragment
        businessFragment.addBusinessNewsToAdapter(newsList)
    }

    private lateinit var mAdapter: NewsRVAdapter
    private lateinit var mPresenter: NewsListPresenter
    private lateinit var pagerAdapter: ViewPagerAdapter
    private var mPageNumber: Int = AppConstants.INITIAL_PAGE_NUMBER

    override fun displayNewsListData(customVO: List<NewsVO>) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        NewsModel.initNewsModel(context = this)
        mPresenter = ViewModelProviders.of(this)
            .get(NewsListPresenter::class.java)
        mPresenter.initPresenter(this)

        mAdapter = NewsRVAdapter(this, mPresenter)
        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        pagerAdapter.run {
            addFragment(BusinessFragment(), "Business")
            addFragment(EntertainmentFragment(),"Entertainment")
        }
        pager.adapter = pagerAdapter
        tabs.setupWithViewPager(pager)
        tabs.setSelectedTabIndicatorColor(applicationContext.resources.getColor(R.color.color_transparent))
        //mPresenter.onLoadBusinessNewsList("us","business", "4f706c8d115f445bbf44cc1e301175ea")
        //observeNewsList()
        onTapBusinessRefreshEmptyView()
        onTapEntertainmentRefreshEmptyView()
        /*mSmartScrollListener = SmartScrollListener(SmartScrollListener.OnSmartScrollListener {
            mPresenter.onLoadMore("us","business","4f706c8d115f445bbf44cc1e301175ea",++mPageNumber)
            observeNewsList()
        })*/


        toolbar.setNavigationOnClickListener { finish() }

    }

    override fun onStart() {
        super.onStart()
    }
    private fun observeNewsList() {
        mPresenter.mBusinessLD!!.observe(this,
            Observer<List<NewsVO>>{newsVO: List<NewsVO>? ->
                displayList(newsVO)
        })
    }
    private fun observeEntertainmentList(){
        mPresenter.mEntertainmentLD!!.observe(this,
            Observer<List<NewsVO>>{newsVO: List<NewsVO>? ->
                displayEntertainmentList(newsVO)
            })
    }
    private fun observeNewsListSmartScroll(){
        mPresenter.mBusinessLD!!.observe(this,
            Observer<List<NewsVO>>{newsVO: List<NewsVO>? ->
                displayNewList(newsVO)
            })
    }
    private fun observeEntertainmentListSmartScroll(){
        mPresenter.mBusinessLD!!.observe(this,
            Observer<List<NewsVO>>{newsVO: List<NewsVO>? ->
                displayNewEntertainmentList(newsVO)
            })
    }

}
