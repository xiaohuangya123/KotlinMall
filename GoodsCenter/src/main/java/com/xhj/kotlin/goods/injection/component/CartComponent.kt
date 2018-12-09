package com.xhj.kotlin.goods.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.goods.injection.module.CartModule
import com.xhj.kotlin.goods.injection.module.GoodsModule
import com.xhj.kotlin.goods.ui.activity.GoodsActivity
import com.xhj.kotlin.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(CartModule::class))
interface CartComponent {
}