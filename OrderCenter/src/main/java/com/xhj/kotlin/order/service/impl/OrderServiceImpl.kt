package com.xhj.kotlin.order.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.base.ext.convertBoolean
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.data.repository.OrderRepository
import com.xhj.kotlin.order.service.OrderService
import io.reactivex.Observable
import javax.inject.Inject

class OrderServiceImpl @Inject constructor() : OrderService {


    @Inject
    lateinit var repository : OrderRepository

    /*
        根据ID查询订单
     */
    override fun getOrderById(orderId: Int): Observable<Order> {
        return repository.getOrderById(orderId).convert()
    }

    /*
        提交订单
     */
    override fun submitOrder(order: Order): Observable<Boolean>{
        return repository.submitOrder(order).convertBoolean()
    }

    /*
        根据状态查询订单列表
     */
    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()
    }

}