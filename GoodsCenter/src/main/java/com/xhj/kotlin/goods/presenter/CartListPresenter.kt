package com.xhj.kotlin.goods.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.goods.data.protocol.CartGoods
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.presenter.view.CartListView
import com.xhj.kotlin.goods.presenter.view.CategoryView
import com.xhj.kotlin.goods.service.CartService
import com.xhj.kotlin.goods.service.CategoryService
import javax.inject.Inject

class CartListPresenter @Inject constructor(): BasePresenter<CartListView>(){

    @Inject
    lateinit var cartService: CartService

    fun getCartList(){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        cartService.getCartList()
            .execute(object : BaseObserver<MutableList<CartGoods>?>(mView){
                override fun onNext(t: MutableList<CartGoods>?) {
                    mView.onGetCartListResult(t)
                }
            },lifecycleProvider)
    }
}

