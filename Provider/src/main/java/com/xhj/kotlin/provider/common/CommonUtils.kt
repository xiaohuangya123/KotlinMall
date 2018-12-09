package com.xhj.kotlin.provider.common

import com.alibaba.android.arouter.launcher.ARouter
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.base.common.BaseConstant
import com.xhj.kotlin.provider.router.RouterPath

/**
 * Author: Created by XHJ on 2018/11/28.
 */

fun isLogined(): Boolean{
    return AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
}

fun afterLogin(method:()->Unit){
    if(isLogined()){
        method()
    }else{
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}