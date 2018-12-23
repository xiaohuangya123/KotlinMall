package com.xhj.kotlin.pay.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.base.ext.convertBoolean
import com.xhj.kotlin.pay.data.repository.PayRepository
import com.xhj.kotlin.pay.service.PayService
import io.reactivex.Observable
import javax.inject.Inject
/**
 * Author: Created by XHJ on 2018/12/20.
 */
class PayServiceImpl @Inject constructor(): PayService {


    @Inject
    lateinit var repository : PayRepository

    /*
     获取支付宝支付签名
  */
    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>{
        return repository.getPaySign(orderId, totalPrice).convert()
    }

    /*
        刷新订单状态已支付
     */
    override fun payOrder(orderId: Int): Observable<Boolean> {
        return repository.payOrder(orderId).convertBoolean()
    }
}