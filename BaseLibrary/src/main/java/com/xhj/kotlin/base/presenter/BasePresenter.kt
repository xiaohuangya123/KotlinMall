package com.xhj.kotlin.base.presenter

import com.xhj.kotlin.base.presenter.view.BaseView

open class BasePresenter <T : BaseView>{
    lateinit var mView : T
}