package com.xhj.kotlin.user.data.repository

import android.util.Log
import com.xhj.kotlin.base.data.net.RetrofitFactory
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.user.data.api.UserApi
import com.xhj.kotlin.user.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
class UserRepository @Inject constructor(){
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .register(RegisterReq(mobile, pwd, verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .login(LoginReq(mobile, pwd, pushId))
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .forgetPwd(ForgetPwdReq(mobile, verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .resetPwd(ResetPwdReq(mobile, pwd))
    }

    fun editUser(userIcon: String, userName: String
                 , userGender: String, userSign: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .editUser(EditUserReq(userIcon, userName, userGender, userSign))
    }
}