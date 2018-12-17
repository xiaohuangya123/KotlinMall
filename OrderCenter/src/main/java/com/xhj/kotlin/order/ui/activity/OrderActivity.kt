package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.common.OrderStatus
import com.xhj.kotlin.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*

/**
 * Author: Created by XHJ on 2018/12/16.
 */
class OrderActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        //跳转到订单页面后直接选中,默认全部
        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }
}