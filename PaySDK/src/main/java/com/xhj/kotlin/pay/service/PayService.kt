package com.xhj.kotlin.pay.service

import io.reactivex.Observable


/**
 * Author: Created by XHJ on 2018/12/20.
 */
interface PayService {
    /*
      获取支付宝支付签名
   */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<Boolean>
}