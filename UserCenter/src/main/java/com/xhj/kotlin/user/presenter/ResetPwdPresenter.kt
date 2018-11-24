package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.presenter.view.ForgetPwdView
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.presenter.view.ResetPwdView
import com.xhj.kotlin.user.service.UserService
import javax.inject.Inject

class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>(){

    @Inject
    lateinit var userService : UserService

    fun resetPwd(mobile :String, pwd :String){
        /**
         * 业务逻辑
         */
        if(!checkNetWork()){
            return
        }

        mView.showLoading()
        userService.resetPwd(mobile,pwd)
            .execute(object : BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if(t){
                        mView.onResetPwdResult("重置密码成功")
                    }
                }
            },lifecycleProvider)
    }

}

