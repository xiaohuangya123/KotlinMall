package com.xhj.kotlin.user.service

import io.reactivex.Flowable
import io.reactivex.Observable

interface UserService {
    fun register(mobile :String, verifyCode :String , pwd :String):Observable<Boolean>
}