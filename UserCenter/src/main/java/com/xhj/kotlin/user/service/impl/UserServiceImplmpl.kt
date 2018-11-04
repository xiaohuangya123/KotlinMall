package com.xhj.kotlin.user.service.impl

import android.util.Log
import com.xhj.kotlin.user.service.UserService
import io.reactivex.Flowable
import io.reactivex.Observable

class UserServiceImplmpl:UserService{
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return Observable.just(true)
    }
}