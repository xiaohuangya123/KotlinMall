package com.xhj.kotlin.base.ext

import com.xhj.kotlin.base.rx.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(mObserver : BaseObserver<T>){
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mObserver)
}