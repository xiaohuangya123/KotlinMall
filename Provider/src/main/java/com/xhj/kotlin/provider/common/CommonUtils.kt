package com.xhj.kotlin.provider.common

import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.base.common.BaseConstant

/**
 * Author: Created by XHJ on 2018/11/28.
 */

fun isLogined(): Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}