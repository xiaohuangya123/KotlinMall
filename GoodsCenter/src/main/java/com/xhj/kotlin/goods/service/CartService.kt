package com.xhj.kotlin.goods.service

import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.goods.data.protocol.CartGoods
import io.reactivex.Observable
import retrofit2.http.POST

interface CartService {
    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<Int>

    /*
       获取购物车列表
    */
    @POST("cart/getList")
    fun getCartList(): Observable<MutableList<CartGoods>?>
}