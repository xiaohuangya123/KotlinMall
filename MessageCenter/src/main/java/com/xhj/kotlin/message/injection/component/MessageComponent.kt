package com.xhj.kotlin.message.injection.component


import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.message.injection.module.MessageModule
import com.xhj.kotlin.message.ui.fragment.MessageFragment
import dagger.Component

/*
    消息模块注入组件
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(MessageModule::class))
interface MessageComponent{
    fun inject(fragment:MessageFragment)
}
