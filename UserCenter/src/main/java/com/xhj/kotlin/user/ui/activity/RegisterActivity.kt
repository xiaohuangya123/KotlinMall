package com.xhj.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xhj.kotlin.base.common.AppManager
import com.xhj.kotlin.base.ext.enable
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.injection.component.DaggerUserComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.presenter.RegisterPresenter
import com.xhj.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * 注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView ,View.OnClickListener{

    private var pressTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()
    }

    //初始化视图
    private fun initView() {
        mRegisterBtn.enable(mMobileEt, {isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt, {isBtnEnable()})
        mRegisterBtn.enable(mPwdEt, {isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt, {isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }
    //注册回调
    override fun onRegisterResult(result: String) {
        toast(result)
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

    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功。")
            }
            R.id.mRegisterBtn ->{
                mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    private fun isBtnEnable() : Boolean{
        return mMobileEt.text.isNullOrBlank().not() &&
                mVerifyCodeEt.text.isNullOrBlank().not() &&
                mPwdEt.text.isNullOrBlank().not() &&
                mPwdConfirmEt.text.isNullOrBlank().not()

    }
}
