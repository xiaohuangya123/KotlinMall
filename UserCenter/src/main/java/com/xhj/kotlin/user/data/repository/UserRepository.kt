package com.xhj.kotlin.user.data.repository

import com.xhj.kotlin.base.data.net.RetrofitFactory
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.user.data.api.UserApi
import com.xhj.kotlin.user.data.protocol.RegisterReq
import io.reactivex.Observable

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
class UserRepository {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .register(RegisterReq(mobile, pwd, verifyCode))
    }
}