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
    //忘记密码
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).convertBoolean()
    }
    //重置密码
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).convertBoolean()
    }
    //编辑用户信息
    override fun editUser(userIcon :String, userName :String
                 , userGender: String, userSign: String ):Observable<UserInfo>{
        return repository.editUser(userIcon, userName, userGender, userSign).convert()
    }
}