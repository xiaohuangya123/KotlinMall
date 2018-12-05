package com.xhj.kotlin.goods.injection.module

import com.xhj.kotlin.goods.service.GoodsService
import com.xhj.kotlin.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}