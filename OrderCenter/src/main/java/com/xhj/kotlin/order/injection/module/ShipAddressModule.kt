package com.xhj.kotlin.order.injection.module

import com.xhj.kotlin.order.service.ShipAddressService
import com.xhj.kotlin.order.service.impl.ShipAddressServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class ShipAddressModule {

    @Provides
    fun providesShipAddressService(shipAddressService: ShipAddressServiceImpl): ShipAddressService {
        return shipAddressService
    }
}