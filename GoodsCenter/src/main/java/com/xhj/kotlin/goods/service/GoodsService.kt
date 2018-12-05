package com.xhj.kotlin.goods.service

import com.xhj.kotlin.goods.data.protocol.Goods
import io.reactivex.Observable

interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>
}