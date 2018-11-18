package com.xhj.kotlin.user.data.api

import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.user.data.protocol.LoginReq
import com.xhj.kotlin.user.data.protocol.RegisterReq
import com.xhj.kotlin.user.data.protocol.UserInfo
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq):Observable<BaseResp<UserInfo>>
}