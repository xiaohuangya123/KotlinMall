package com.xhj.kotlin.user.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.base.ext.convertBoolean
import com.xhj.kotlin.user.data.protocol.UserInfo
import com.xhj.kotlin.user.data.repository.UserRepository
import com.xhj.kotlin.user.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() :UserService{

    @Inject
    lateinit var repository : UserRepository

    //注册
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode)
            .convertBoolean()
    }
    //登录
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId)
            .convert()
    }
}