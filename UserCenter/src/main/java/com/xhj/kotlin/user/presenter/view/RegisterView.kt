package com.xhj.kotlin.user.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView

interface RegisterView : BaseView{
    fun onRegisterResult(result: Boolean)
}