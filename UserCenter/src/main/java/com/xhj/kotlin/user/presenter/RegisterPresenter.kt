package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.data.repository.UserRepository
import com.xhj.kotlin.user.presenter.view.RegisterView
import com.xhj.kotlin.user.service.impl.UserServiceImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class RegisterPresenter : BasePresenter<RegisterView>(){

    fun register(mobile :String, pwd :String, verifyCode :String){
        /**
         * 业务逻辑
         */
//        val repository = UserRepository()
//
//        var resp = BaseResp<String>(1,"OK","data")
//        repository.register(mobile,pwd,verifyCode)
//            .flatMap ({ resp -> Observable.just(resp.status == 0) })
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe{result -> mView.onRegisterResult(result)}



        var userService = UserServiceImpl()
        userService.register(mobile,pwd,verifyCode)
            .execute(object : BaseObserver<Boolean>(){
                override fun onNext(t: Boolean) {
                    mView.onRegisterResult(t)
                }
            })
        mView.onRegisterResult(true)
    }

}

