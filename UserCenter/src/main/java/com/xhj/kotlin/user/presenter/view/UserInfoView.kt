package com.xhj.kotlin.user.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.user.data.protocol.UserInfo

interface UserInfoView : BaseView{

    fun onGetUploadTokenResult(result:String)

    fun onEditUserResult(result: UserInfo)

}