package com.xhj.kotlin.order.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.presenter.view.OrderConfirmView
import com.xhj.kotlin.order.service.OrderService
import javax.inject.Inject

class OrderConfirmPresenter @Inject constructor(): BasePresenter<OrderConfirmView>(){

    @Inject
    lateinit var orderService : OrderService

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        orderService.getOrderById(orderId)
            .execute(object : BaseObserver<Order>(mView){
                override fun onNext(t: Order) {
                    mView.onGetOrderByIdResult(t)
                }
            },lifecycleProvider)
    }
}

