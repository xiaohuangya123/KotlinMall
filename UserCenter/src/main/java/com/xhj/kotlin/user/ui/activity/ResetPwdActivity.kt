package com.xhj.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xhj.kotlin.base.ext.enable
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.injection.component.DaggerUserComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.presenter.ResetPwdPresenter
import com.xhj.kotlin.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

/**
 * 忘记密码界面
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }

    //初始化视图
    private fun initView() {
        mConfirmBtn.enable(mPwdEt, {isBtnEnable()})
        mConfirmBtn.enable(mPwdConfirmEt, {isBtnEnable()})

        mConfirmBtn.onClick{
            if(mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }

    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }


    private fun isBtnEnable() : Boolean{
        return mPwdEt.text.isNullOrBlank().not() &&
                mPwdConfirmEt.text.isNullOrBlank().not()
    }

    override fun onResetPwdResult(result: String) {
        toast("重置密码成功")
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}
