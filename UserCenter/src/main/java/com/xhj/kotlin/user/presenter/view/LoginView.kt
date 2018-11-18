package com.xhj.kotlin.user.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.user.data.protocol.UserInfo

interface LoginView : BaseView{
    fun onLoginResult(result: UserInfo)
}