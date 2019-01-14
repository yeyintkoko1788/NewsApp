package com.example.davidnaing.newsapp.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.davidnaing.newsapp.R
import com.example.davidnaing.newsapp.activities.MainActivity
import com.example.davidnaing.newsapp.adapters.NewsRVAdapter
import com.example.davidnaing.newsapp.components.SmartScrollListener
import com.example.davidnaing.newsapp.components.SpacesItemDecoration
import com.example.davidnaing.newsapp.data.VOs.NewsVO
import com.example.davidnaing.newsapp.delegate.NewListPresenterDelegate
import com.example.davidnaing.newsapp.mvp.Presenters.NewsListPresenter
import com.example.davidnaing.newsapp.utils.AppConstants
import com.yammobots.paradiso.bod.components.SmartRecyclerView
import com.yammobots.paradiso.components.EmptyViewPod
import kotlinx.android.synthetic.main.empty_view_pod.view.*

class BusinessFragment : Fragment() {

    private lateinit var mPresenter : NewsListPresenter
    private lateinit var mHomePresenterDelegate: NewListPresenterDelegate
    private lateinit var mAdapter : NewsRVAdapter
    private lateinit var linearLayoutManagerVertical: LinearLayoutManager
    private var mSmartScrollListener: SmartScrollListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragemtn_business,container,false)
        mPresenter = mHomePresenterDelegate.getPresenter()
        mAdapter = NewsRVAdapter(context!!,mPresenter)
        mSmartScrollListener = SmartScrollListener(SmartScrollListener.OnSmartScrollListener {
            //mPresenter.onLoadMore("us","business","4f706c8d115f445bbf44cc1e301175ea",++mPageNumber)
            mPresenter.onSmartScroll()
        })
        linearLayoutManagerVertical = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        val rvBusiness : SmartRecyclerView = view.findViewById(R.id.rv_business)
        val emptyViewPod : EmptyViewPod = view.findViewById(R.id.vpEmpty)

        emptyViewPod.setEmptyData(R.drawable.ic_news,"No News To Display")
        rvBusiness.setEmptyView(emptyViewPod)
        rvBusiness.layoutManager = linearLayoutManagerVertical
        rvBusiness.setHasFixedSize(true)
        rvBusiness.addItemDecoration(SpacesItemDecoration(12))
        rvBusiness.adapter = mAdapter
        rvBusiness.addOnScrollListener(mSmartScrollListener!!)


        emptyViewPod.btn_refresh_empty.setOnClickListener{
            mPresenter.onTapBusinessNewsRefresh()
        }

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mHomePresenterDelegate = context as NewListPresenterDelegate
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        mPresenter.onTapBusinessNewsRefresh()
    }
    fun addBusinessNewsToAdapter(newsVO: List<NewsVO>?){
        mAdapter.clearData()
        mAdapter.appendNewData(newsVO!!)
    }
    fun addNewBusinessNewsToAdapter(newsVO: List<NewsVO>?){
        mAdapter.appendNewData(newsVO!!)
    }
}