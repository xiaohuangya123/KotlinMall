package com.xhj.kotlin.user.presenter


import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.user.data.protocol.UserInfo
import com.xhj.kotlin.user.presenter.view.UserInfoView
import com.xhj.kotlin.user.service.UploadService
import com.xhj.kotlin.user.service.UserService
import javax.inject.Inject

class UserInfoPresenter @Inject constructor(): BasePresenter<UserInfoView>(){

    @Inject
    lateinit var userService : UserService

    @Inject
    lateinit var uploadService : UploadService

    fun getUploadToken(){
        if(!checkNetWork())
            return

        mView.showLoading()
        uploadService.getUploadToken().execute(object : BaseObserver<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)

    }

    fun editUser(userIcon :String, userName :String
                 , userGender: String, userSign: String ){
        if(!checkNetWork())
            return

        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).execute(object : BaseObserver<UserInfo>(mView){
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }

}

