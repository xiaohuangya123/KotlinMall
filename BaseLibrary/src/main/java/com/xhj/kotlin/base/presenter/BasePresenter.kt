package com.xhj.kotlin.base.presenter

import com.trello.rxlifecycle3.LifecycleProvider
import com.xhj.kotlin.base.presenter.view.BaseView
import javax.inject.Inject

open class BasePresenter <T : BaseView>{
    lateinit var mView : T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
}