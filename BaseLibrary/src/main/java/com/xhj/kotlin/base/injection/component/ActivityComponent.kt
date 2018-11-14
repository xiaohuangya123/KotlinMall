package com.xhj.kotlin.base.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle3.LifecycleProvider
import com.xhj.kotlin.base.injection.ActivityScope
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.LifecycleProviderModule
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class)
    ,modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity() :Activity
    fun context() : Context
    fun lifecycleProvider() : LifecycleProvider<*>
}