package com.xhj.kotlin.base.ext

import com.trello.rxlifecycle3.LifecycleProvider
import com.xhj.kotlin.base.rx.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(mObserver : BaseObserver<T>,lifecycleProvider: LifecycleProvider<*>){
    subscribeOn(Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mObserver)
}