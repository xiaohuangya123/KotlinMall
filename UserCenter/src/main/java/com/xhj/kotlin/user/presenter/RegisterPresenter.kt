package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.service.impl.UserServiceImpl

class RegisterPresenter : BasePresenter<RegisterView>(){

    fun register(mobile :String, pwd :String, verifyCode :String){
        /**
         * 业务逻辑
         */
        var userService = UserServiceImpl()
        userService.register(mobile,pwd,verifyCode)
            .execute(object : BaseObserver<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            })
//        mView.onRegisterResult(true)
    }

}

