package com.xhj.kotlin.goods.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.presenter.view.GoodsDetailView
import com.xhj.kotlin.goods.presenter.view.GoodsListView
import com.xhj.kotlin.goods.service.GoodsService
import javax.inject.Inject

/*
    商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

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
}
