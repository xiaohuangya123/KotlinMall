package com.xhj.kotlin.goods.service

import com.xhj.kotlin.base.data.net.RetrofitFactory
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.goods.data.api.CartApi
import com.xhj.kotlin.goods.data.protocol.CartGoods
import com.xhj.kotlin.goods.data.protocol.DeleteCartReq
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

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<Boolean>
}