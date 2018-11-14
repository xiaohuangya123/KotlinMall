package com.xhj.kotlin.base.common

import android.app.Application
import com.orhanobut.logger.*
import com.xhj.kotlin.base.injection.component.AppComponent
import com.xhj.kotlin.base.injection.component.DaggerAppComponent
import com.xhj.kotlin.base.injection.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

       //应用logger日志框架
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("myTag")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        ininAppInjection()
    }

    private fun ininAppInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}