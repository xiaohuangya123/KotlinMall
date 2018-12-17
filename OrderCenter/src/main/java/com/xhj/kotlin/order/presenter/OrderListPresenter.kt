package com.xhj.kotlin.order.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.presenter.view.OrderListView
import com.xhj.kotlin.order.service.OrderService
import javax.inject.Inject

class OrderListPresenter @Inject constructor(): BasePresenter<OrderListView>(){

    @Inject
    lateinit var orderService : OrderService

    /*
         根据状态查询订单列表
     */
    fun getOrderList(orderStatus: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        orderService.getOrderList(orderStatus)
            .execute(object : BaseObserver<MutableList<Order>?>(mView){
                override fun onNext(t: MutableList<Order>?) {
                    mView.onGetOrderListResult(t)
                }
            },lifecycleProvider)
    }

    /*
         确认收获订单
     */
    fun confirmOrder(orderId: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        orderService.confirmOrder(orderId)
            .execute(object : BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    mView.onConfirmOrderResult(t)
                }
            },lifecycleProvider)
    }

    /*
         取消订单
     */
    fun cancelOrder(orderId: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        orderService.cancelOrder(orderId)
            .execute(object : BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    mView.oncCancelOrderResult(t)
                }
            },lifecycleProvider)
    }

}

