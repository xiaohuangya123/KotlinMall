package com.xhj.kotlin.user.service

import com.xhj.kotlin.user.data.protocol.UserInfo
import io.reactivex.Observable

interface UserService {
    fun register(mobile :String, pwd :String, verifyCode :String ):Observable<Boolean>

    fun login(mobile :String, pwd :String, pushId :String ):Observable<UserInfo>

    fun forgetPwd(mobile :String, verifyCode: String):Observable<Boolean>

    fun resetPwd(mobile :String, pwd :String):Observable<Boolean>
}