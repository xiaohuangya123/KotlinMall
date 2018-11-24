package com.xhj.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xhj.kotlin.base.ext.enable
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.injection.component.DaggerUserComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.presenter.ForgetPwdPresenter
import com.xhj.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView ,View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    //初始化视图
    private fun initView() {
        mNextBtn.enable(mMobileEt, {isBtnEnable()})
        mNextBtn.enable(mVerifyCodeEt, {isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)

    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn ->{
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证码成功。")
            }
            R.id.mNextBtn ->{
                mPresenter.forgetPwd(mMobileEt.text.toString(),mVerifyCodeEt.text.toString())
            }
        }
    }

    private fun isBtnEnable() : Boolean{
        return mMobileEt.text.isNullOrBlank().not() &&
                mVerifyCodeEt.text.isNullOrBlank().not()
    }

    override fun onForgetPwdResult(result: String) {
        toast("验证成功")
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }
}
