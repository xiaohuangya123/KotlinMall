package com.xhj.kotlin.goods.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.goods.data.protocol.Goods
import com.xhj.kotlin.goods.data.repository.GoodsRepository
import com.xhj.kotlin.goods.service.GoodsService
import io.reactivex.Observable
import javax.inject.Inject

class GoodsServiceImpl @Inject constructor() :GoodsService{

    @Inject
    lateinit var repository : GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsList(categoryId, pageNo).convert()
    }

    /*
       根据关键字查询商品
    */
    override fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?> {
        return repository.getGoodsListByKeyword(keyword,pageNo).convert()
    }

    /*
        获取商品详情
     */
    override fun getGoodsDetail(goodsId: Int): Observable<Goods> {
        return repository.getGoodsDetail(goodsId).convert()
    }

}