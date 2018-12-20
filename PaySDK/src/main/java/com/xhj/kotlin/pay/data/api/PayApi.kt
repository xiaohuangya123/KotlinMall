package com.xhj.kotlin.pay.data.api

import retrofit2.http.Body
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.pay.data.protocol.GetPaySignReq
import com.xhj.kotlin.pay.data.protocol.PayOrderReq
import io.reactivex.Observable
import retrofit2.http.POST


/*
    支付 接口
 */
interface PayApi {

    /*
        获取支付宝支付签名
     */
    @POST("pay/getPaySign")
    fun getPaySign(@Body req: GetPaySignReq): Observable<BaseResp<String>>

    /*
        刷新订单状态，已支付
     */
    @POST("order/pay")
    fun payOrder(@Body req: PayOrderReq): Observable<BaseResp<String>>

}
