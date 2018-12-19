package com.xhj.kotlin.base.common

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.*
import com.xhj.kotlin.base.injection.component.AppComponent
import com.xhj.kotlin.base.injection.component.DaggerAppComponent
import com.xhj.kotlin.base.injection.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    /*
        全局伴生对象
     */
    companion object {
        lateinit var context : Context
    }

    override fun onCreate() {
        super.onCreate()
        //LeakCanary内存泄漏检查工具初始化
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
       //应用logger日志框架
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .tag("myTag")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        initAppInjection()
        context = this

        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        ARouter.openLog()// Print log
        ARouter.openDebug()// Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this)// As early as possible
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}