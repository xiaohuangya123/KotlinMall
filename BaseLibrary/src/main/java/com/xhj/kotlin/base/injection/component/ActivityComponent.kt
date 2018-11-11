package com.xhj.kotlin.base.injection.component

import android.app.Activity
import android.content.Context
import com.xhj.kotlin.base.injection.ActivityScope
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity() :Activity
}