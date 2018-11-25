package com.xhj.kotlin.user.data.repository

import android.util.Log
import com.xhj.kotlin.base.data.net.RetrofitFactory
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.user.data.api.UploadApi
import com.xhj.kotlin.user.data.api.UserApi
import com.xhj.kotlin.user.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
class UploadRepository @Inject constructor(){
    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java)
            .getUploadToken()
    }


}