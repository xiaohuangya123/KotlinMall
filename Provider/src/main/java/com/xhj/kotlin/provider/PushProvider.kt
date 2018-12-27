package com.xhj.kotlin.provider

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Author: Created by XHJ on 2018/12/24.
 */
interface PushProvider:IProvider {
    fun getPushId():String
}