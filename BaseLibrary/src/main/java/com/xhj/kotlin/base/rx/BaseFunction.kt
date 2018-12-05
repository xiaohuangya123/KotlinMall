package com.xhj.kotlin.base.rx

import android.util.Log
import com.xhj.kotlin.base.common.ResultCode
import com.xhj.kotlin.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * Author: Created by XHJBB on 2018/11/15.
 */
class BaseFunction<T> : Function<BaseResp<T>, ObservableSource<T>>{
    override fun apply(t: BaseResp<T>): ObservableSource<T> {

        if (t.status != ResultCode.SUCCESS ) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }

}