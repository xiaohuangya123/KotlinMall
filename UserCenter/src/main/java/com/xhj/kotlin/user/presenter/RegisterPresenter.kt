package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.service.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>(){

    @Inject
    lateinit var userService : UserService

    fun register(mobile :String, pwd :String, verifyCode :String){
        /**
         * 业务逻辑
         */
        userService.register(mobile,pwd,verifyCode)
            .execute(object : BaseObserver<Boolean>(){
                override fun onNext(t: Boolean) {
                    if(t){
                        mView.onRegisterResult("注册成功")
                    }

                }
            },lifecycleProvider)

//            .flatMap ({ resp -> Observable.just(resp.status == 0) })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{result -> mView.onRegisterResult(result)}

    }

}

