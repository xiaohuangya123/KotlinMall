package com.xhj.kotlin.pay.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.pay.presenter.view.PayView
import com.xhj.kotlin.pay.service.PayService
import javax.inject.Inject

/**
 * Author: Created by XHJ on 2018/12/20.
 */
class PayPresenter @Inject constructor(): BasePresenter<PayView>() {

    @Inject
    lateinit var payService:PayService

    fun getPaySign(orderId: Int, totalPrice: Long){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        payService.getPaySign(orderId, totalPrice)
            .execute(object : BaseObserver<String>(mView){
                override fun onNext(t: String) {
                    mView.onGetPaySignResult(t)
                }
            },lifecycleProvider)
    }
}