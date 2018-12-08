package com.xhj.kotlin.goods.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.presenter.view.GoodsListView
import com.xhj.kotlin.goods.service.GoodsService
import javax.inject.Inject

class GoodsListPresenter @Inject constructor(): BasePresenter<GoodsListView>(){

    @Inject
    lateinit var goodsService : GoodsService

    fun getGoodsList(categoryId: Int, pageNo: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo)
            .execute(object : BaseObserver<MutableList<Goods>?>(mView){
                override fun onNext(t: MutableList<Goods>?) {
                    mView.onGetGoodsListResult(t)
                }
            },lifecycleProvider)
    }

    /*
        根据关键字 搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword,pageNo).execute(object : BaseObserver<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        }, lifecycleProvider)

    }
}

