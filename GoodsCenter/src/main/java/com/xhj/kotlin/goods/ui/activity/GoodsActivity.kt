package com.xhj.kotlin.goods.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.injection.component.DaggerGoodsComponent
import com.xhj.kotlin.goods.injection.module.GoodsModule
import com.xhj.kotlin.goods.presenter.GoodsListPresenter
import com.xhj.kotlin.goods.presenter.view.GoodsListView
import com.xhj.kotlin.goods.ui.adapter.GoodsAdapter
import kotlinx.android.synthetic.main.activity_goods.*


/**
 * Author: Created by XHJ on 2018/12/4.
 */
class GoodsActivity: BaseMvpActivity<GoodsListPresenter>(), GoodsListView,
    BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var mGoodsAdapter: GoodsAdapter
    private var mCurrentPage = 1
    private var mMaxPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setDelegate(this)
        var viewHolder = BGANormalRefreshViewHolder(this, true)
        viewHolder.setLoadMoreBackgroundColorRes(R.color.common_bg)
        viewHolder.setRefreshViewBackgroundColorRes(R.color.common_bg)
        mRefreshLayout.setRefreshViewHolder(viewHolder)
    }

    private fun loadData() {
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            mMultiStateView.startLoading()
            mPresenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), mCurrentPage)
        } else {
            mMultiStateView.startLoading()
            mPresenter.getGoodsList(intent.getIntExtra(GoodsConstant.KEY_CATEGORY_ID, 1), mCurrentPage)
        }
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if(result != null && result.size>0 ){
            mMaxPage = result[0].maxPage
            if(mCurrentPage == 1) {//如果目前是第一页请求最新数据，也就是第一页数据
                mGoodsAdapter.setData(result)
            }else{//在上拉加载更多的时候，需要把之前的数据和新请求的数据合并，一起显示
                mGoodsAdapter.dataList.addAll(result)
                mGoodsAdapter.notifyDataSetChanged()
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder()
            .activityComponent(activityComponent)
            .goodsModule(GoodsModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if(mCurrentPage<mMaxPage){
            mCurrentPage++
            loadData()
            true
        }else{
            false
        }
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        mCurrentPage = 1
        loadData()
    }
}