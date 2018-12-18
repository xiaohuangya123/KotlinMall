package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderDetailPresenter
import com.xhj.kotlin.order.presenter.view.OrderDetailView
import com.xhj.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.xhj.kotlin.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.activity_order_detail.*

/**
 * Author: Created by XHJ on 2018/12/18.
 */
class OrderDetailActivity:BaseMvpActivity<OrderDetailPresenter>(), OrderDetailView {

    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        loadData()
    }

    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1))
    }

    /**
     * 根据ID查询订单 回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
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