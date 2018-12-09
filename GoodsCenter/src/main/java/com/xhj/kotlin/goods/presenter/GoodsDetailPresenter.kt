package com.xhj.kotlin.goods.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.presenter.view.GoodsDetailView
import com.xhj.kotlin.goods.service.CartService
import com.xhj.kotlin.goods.service.GoodsService
import javax.inject.Inject

/*
    商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    /*
        获取商品详情
     */
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).execute(object : BaseObserver<Goods>(mView) {
            override fun onNext(t: Goods) {
                    mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)
    }

    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int,goodsDesc: String,goodsIcon: String,goodsPrice: Long,
            goodsCount: Int,goodsSku: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku)
            .execute(object : BaseObserver<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onAddCartResult(t)
            }
        }, lifecycleProvider)
    }
}
