package com.xhj.kotlin.order.service
import com.xhj.kotlin.order.data.protocol.Order
import io.reactivex.Observable

interface OrderService {
    /*
         根据ID查询订单
      */
    fun getOrderById(orderId: Int): Observable<Order>

    /*
        提交订单
     */
    fun submitOrder(order: Order): Observable<Boolean>

    /*
       根据状态查询订单列表
    */
    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>
}