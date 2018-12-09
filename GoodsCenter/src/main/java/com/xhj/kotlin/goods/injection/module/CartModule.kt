package com.xhj.kotlin.goods.injection.module

import com.xhj.kotlin.goods.service.CartService
import com.xhj.kotlin.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class CartModule {

    @Provides
    fun providesCartService(cartService: CartServiceImpl): CartService {
        return cartService
    }
}