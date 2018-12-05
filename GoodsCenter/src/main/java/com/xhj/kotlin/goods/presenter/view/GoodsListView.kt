package com.xhj.kotlin.goods.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.goods.data.protocol.Goods

interface GoodsListView : BaseView{
    fun onGetGoodsListResult(result: MutableList<Goods>?)
}