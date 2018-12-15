package com.xhj.kotlin.order.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.order.data.protocol.Order

interface OrderConfirmView : BaseView{
    fun onGetOrderByIdResult(result: Order)
}