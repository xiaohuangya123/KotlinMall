package com.xhj.kotlin.user.ui.activity

import android.os.Bundle
import com.orhanobut.logger.Logger
import com.xhj.kotlin.base.common.AppManager
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.injection.component.DaggerUserComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.presenter.RegisterPresenter
import com.xhj.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    private var pressTime : Long = 0

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
        Logger.d("hello")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick{
            mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
        }
    }

    override fun onBackPressed() {
        var time = System.currentTimeMillis()
        if(time - pressTime > 2000){
            toast("在按一次退出应用程序")
            pressTime = time
        }else{
            AppManager.instance.exitApp(this)
        }
    }
}
