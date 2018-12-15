package com.xhj.kotlin.order.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.order.injection.module.ShipAddressModule
import com.xhj.kotlin.order.ui.activity.ShipAddressActivity
import com.xhj.kotlin.order.ui.activity.ShipAddressEditActivity
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(ShipAddressModule::class))
interface ShipAddressComponent {
    fun inject(activity: ShipAddressEditActivity)
    fun inject(activity: ShipAddressActivity)
}