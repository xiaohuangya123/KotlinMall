package com.xhj.kotlin.user.service.impl

import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.base.rx.BaseException
import com.xhj.kotlin.user.data.repository.UserRepository
import com.xhj.kotlin.user.service.UserService
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

class UserServiceImpl:UserService{
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        val repository = UserRepository()
       return repository.register(mobile, pwd, verifyCode)
            .flatMap(object : Function<BaseResp<String>, ObservableSource<Boolean>>{
                override fun apply(t: BaseResp<String>): Observable<Boolean> {
                    if(t.status != 0){
                        return Observable.error(BaseException(t.status,t.message))
                    }
                    return Observable.just(true)
                }

            })
    }
}