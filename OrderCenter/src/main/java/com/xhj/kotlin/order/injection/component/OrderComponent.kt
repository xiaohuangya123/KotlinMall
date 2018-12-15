package com.xhj.kotlin.order.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.ui.activity.OrderConfirmActivity
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(OrderModule::class))
interface OrderComponent {
    fun inject(activity: OrderConfirmActivity)
}