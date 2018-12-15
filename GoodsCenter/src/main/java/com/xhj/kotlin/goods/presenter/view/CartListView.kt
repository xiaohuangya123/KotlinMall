package com.xhj.kotlin.goods.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.goods.data.protocol.CartGoods

interface CartListView : BaseView{
    fun onGetCartListResult(result: MutableList<CartGoods>?)
    fun onDeleteCartListResult(result: Boolean)
    fun onSubmitCartListResult(result: Int)
}