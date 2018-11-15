package com.xhj.kotlin.user.service.impl

import com.xhj.kotlin.base.ext.convertBoolean
import com.xhj.kotlin.user.data.repository.UserRepository
import com.xhj.kotlin.user.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor() :UserService{

    @Inject
    lateinit var repository : UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode)
            .convertBoolean()
    }
}