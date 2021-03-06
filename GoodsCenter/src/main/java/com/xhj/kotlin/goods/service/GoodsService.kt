package com.xhj.kotlin.goods.service

import com.xhj.kotlin.goods.data.protocol.Goods
import io.reactivex.Observable

interface GoodsService {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>

    /*
       根据关键字查询商品
    */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>
    /*
        获取商品详情
    */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>
}