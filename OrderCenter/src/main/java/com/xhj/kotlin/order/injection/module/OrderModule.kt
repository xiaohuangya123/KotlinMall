package com.xhj.kotlin.order.injection.module

import com.xhj.kotlin.order.service.OrderService
import com.xhj.kotlin.order.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class OrderModule {

    @Provides
    fun providesOrderService(orderService: OrderServiceImpl): OrderService {
        return orderService
    }
}