package com.xhj.kotlin.base.presenter

import android.content.Context
import com.trello.rxlifecycle3.LifecycleProvider
import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.base.utils.NetWorkUtils
import javax.inject.Inject

open class BasePresenter <T : BaseView>{
    lateinit var mView : T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
    @Inject
    lateinit var context : Context

    fun checkNetWork() : Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}