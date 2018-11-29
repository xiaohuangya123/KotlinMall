package com.xhj.kotlin.goods.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.goods.injection.module.CategoryrModule
import com.xhj.kotlin.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class)
    ,modules = arrayOf(CategoryrModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)

}