package com.xhj.kotlin.base.common

import android.app.Application
import com.xhj.kotlin.base.injection.component.AppComponent
import com.xhj.kotlin.base.injection.component.DaggerAppComponent
import com.xhj.kotlin.base.injection.module.AppModule

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        ininAppInjection()
    }

    private fun ininAppInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}