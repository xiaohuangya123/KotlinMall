package com.xhj.kotlin.base.injection.component

import android.content.Context
import com.xhj.kotlin.base.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context() :Context
}