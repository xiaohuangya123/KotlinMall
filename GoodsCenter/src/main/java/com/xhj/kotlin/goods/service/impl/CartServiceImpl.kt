package com.xhj.kotlin.goods.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.goods.data.protocol.CartGoods
import com.xhj.kotlin.goods.data.repository.CartRepository
import com.xhj.kotlin.goods.service.CartService
import io.reactivex.Observable
import javax.inject.Inject

class CartServiceImpl @Inject constructor() :CartService{

    @Inject
    lateinit var repository : CartRepository

    /*
        添加商品到购物车
     */
    override fun addCart(
        goodsId: Int,
        goodsDesc: String,
        goodsIcon: String,
        goodsPrice: Long,
        goodsCount: Int,
        goodsSku: String
    ): Observable<Int> {
        return repository.addCart(goodsId, goodsDesc
            , goodsIcon, goodsPrice, goodsCount, goodsSku).convert()
    }

    /*
      获取购物车列表
   */
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }



}