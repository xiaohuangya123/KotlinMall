package com.xhj.kotlin.pay.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.pay.injection.module.PayModule
import com.xhj.kotlin.pay.ui.activity.CashRegisterActivity
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(PayModule::class))
interface PayComponent {
    fun inject(activity: CashRegisterActivity)

}