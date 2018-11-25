package com.xhj.kotlin.user.data.api

import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.user.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken():Observable<BaseResp<String>>

}