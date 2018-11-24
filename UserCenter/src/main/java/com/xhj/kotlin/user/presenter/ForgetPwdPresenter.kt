package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.presenter.view.ForgetPwdView
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.service.UserService
import javax.inject.Inject

class ForgetPwdPresenter @Inject constructor(): BasePresenter<ForgetPwdView>(){

    @Inject
    lateinit var userService : UserService

    fun forgetPwd(mobile :String, verifyCode :String){
        /**
         * 业务逻辑
         */
        if(!checkNetWork()){
            return
        }

        mView.showLoading()
        userService.forgetPwd(mobile,verifyCode)
            .execute(object : BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    if(t){
                        mView.onForgetPwdResult("验证成功")
                    }

                }
            },lifecycleProvider)

//            .flatMap ({ resp -> Observable.just(resp.status == 0) })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{result -> mView.onRegisterResult(result)}

    }

}

