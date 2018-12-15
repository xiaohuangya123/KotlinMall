package com.xhj.kotlin.order.service
import com.xhj.kotlin.order.data.protocol.Order
import io.reactivex.Observable

interface OrderService {
    /*
         根据ID查询订单
      */
    fun getOrderById(orderId: Int): Observable<Order>
}