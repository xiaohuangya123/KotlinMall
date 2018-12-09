package com.xhj.kotlin.goods.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.goods.data.protocol.Goods

/*
    商品详情 视图回调
 */
interface GoodsDetailView : BaseView {

    //获取商品详情
    fun onGetGoodsDetailResult(result: Goods)

    fun onAddCartResult(result: Int)

}
