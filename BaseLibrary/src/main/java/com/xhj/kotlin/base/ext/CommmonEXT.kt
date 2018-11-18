package com.xhj.kotlin.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.trello.rxlifecycle3.LifecycleProvider
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.base.rx.BaseFunction
import com.xhj.kotlin.base.rx.BaseFunctionBoolean
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.base.widgets.DefaultTextWatcher
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.execute(mObserver : BaseObserver<T>,lifecycleProvider: LifecycleProvider<*>){
    subscribeOn(Schedulers.io())
        .compose(lifecycleProvider.bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mObserver)
}

fun <T> Observable<BaseResp<T>>.convert() : Observable<T>{
    return this.flatMap(BaseFunction())
}

fun <T> Observable<BaseResp<T>>.convertBoolean() : Observable<Boolean>{
    return this.flatMap(BaseFunctionBoolean())
}

fun View.onClick(listener : View.OnClickListener){
    this.setOnClickListener(listener)
}

fun View.onClick(method : ()->Unit){
    this.setOnClickListener{method()}
}

fun Button.enable(et:EditText, method: () -> Boolean){
    var btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}