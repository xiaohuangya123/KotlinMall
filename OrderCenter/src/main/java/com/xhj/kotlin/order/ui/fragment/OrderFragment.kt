package com.xhj.kotlin.order.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderListPresenter
import com.xhj.kotlin.order.presenter.view.OrderListView
import com.xhj.kotlin.order.ui.adapter.OrderAdapter
import kotlinx.android.synthetic.main.fragment_order.*

/**
 * Author: Created by XHJ on 2018/12/16.
 */
class OrderFragment:BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter:OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ininView()
        loadData()
    }

    /**
     * 初始化试图
     */
    private fun ininView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity as Activity)
        mOrderRv.adapter = mAdapter
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    /*
       根据状态查询订单列表 回调
    */
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if(result != null && result.size >0){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
            .activityComponent(activityComponent)
            .orderModule(OrderModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }
}