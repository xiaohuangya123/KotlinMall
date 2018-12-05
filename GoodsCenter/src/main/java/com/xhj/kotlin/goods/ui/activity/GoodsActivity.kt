package com.xhj.kotlin.goods.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.goods.R
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
class GoodsActivity: BaseMvpActivity<GoodsListPresenter>(), GoodsListView {

    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)

        initView()
        loadData()
    }

    private fun loadData() {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
        mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), 1)
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        if(result != null && result.size>0 ){
            mGoodsAdapter.setData(result)
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
}