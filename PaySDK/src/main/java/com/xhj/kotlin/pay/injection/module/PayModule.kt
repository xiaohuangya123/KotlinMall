package com.xhj.kotlin.pay.injection.module

import com.xhj.kotlin.pay.service.PayService
import com.xhj.kotlin.pay.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class PayModule {

    @Provides
    fun providesPayService(payService: PayServiceImpl): PayService {
        return payService
    }
}