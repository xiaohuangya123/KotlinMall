package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.service.impl.UserServiceImplmpl

class RegisterPresenter : BasePresenter<RegisterView>(){

    fun register(mobile :String, verifyCode :String, pwd :String){
        /**
         * 业务逻辑
         */
        var userService = UserServiceImplmpl()
        userService.register(mobile,verifyCode,pwd)
            .execute(object : BaseObserver<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            })
//        mView.onRegisterResult(true)
    }

}

