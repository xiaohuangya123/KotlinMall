package com.xhj.kotlin.base.rx

import com.xhj.kotlin.base.common.ResultCode
import com.xhj.kotlin.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * Author: Created by XHJBB on 2018/11/15.
 */
class BaseFunctionBoolean<T> : Function<BaseResp<T>, ObservableSource<Boolean>>{
    override fun apply(t: BaseResp<T>): ObservableSource<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }

}