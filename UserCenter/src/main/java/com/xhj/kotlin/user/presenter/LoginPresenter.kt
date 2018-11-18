package com.xhj.kotlin.user.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.data.protocol.UserInfo
import com.xhj.kotlin.user.presenter.view.LoginView
import com.xhj.kotlin.user.service.UserService
import javax.inject.Inject

class LoginPresenter @Inject constructor(): BasePresenter<LoginView>(){

    @Inject
    lateinit var userService : UserService

    fun login(mobile :String, pwd :String, pushId :String){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.login(mobile,pwd,pushId)
            .execute(object : BaseObserver<UserInfo>(mView){
                override fun onNext(t: UserInfo) {
                    mView.onLoginResult(t)
                }
            },lifecycleProvider)
    }
}

