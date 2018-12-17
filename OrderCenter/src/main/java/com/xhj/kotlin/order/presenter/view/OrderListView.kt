package com.xhj.kotlin.order.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.order.data.protocol.Order

interface OrderListView : BaseView{
    fun onGetOrderListResult(result: MutableList<Order>?)
    fun oncCancelOrderResult(result: Boolean)
    fun onConfirmOrderResult(result: Boolean)
}