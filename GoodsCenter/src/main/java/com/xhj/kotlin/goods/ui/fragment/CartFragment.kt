package com.xhj.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.data.protocol.CartGoods
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.injection.component.DaggerCartComponent
import com.xhj.kotlin.goods.injection.component.DaggerCategoryComponent
import com.xhj.kotlin.goods.injection.module.CartModule
import com.xhj.kotlin.goods.injection.module.CategoryModule
import com.xhj.kotlin.goods.presenter.CartListPresenter
import com.xhj.kotlin.goods.presenter.CategoryPresenter
import com.xhj.kotlin.goods.presenter.view.CartListView
import com.xhj.kotlin.goods.presenter.view.CategoryView
import com.xhj.kotlin.goods.ui.adapter.CartGoodsAdapter
import kotlinx.android.synthetic.main.fragment_cart.*

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class CartFragment: BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter:CartGoodsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    /**
        初始化视图
     */
    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }


    override fun injectComponent() {

        DaggerCartComponent.builder()
            .activityComponent(activityComponent)
            .cartModule(CartModule())
            .build()
            .inject(this)
        mPresenter.mView = this
    }
    /**
        获取购物车商品列表 回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if(result != null && result.size>0 ){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}