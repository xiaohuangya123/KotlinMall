package com.xhj.kotlin.mall.common

import cn.jpush.android.api.JPushInterface
import com.xhj.kotlin.base.common.BaseApplication

/**
 * Author: Created by XHJ on 2018/12/23.
 */
class MainApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        //初始化极光推送
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}