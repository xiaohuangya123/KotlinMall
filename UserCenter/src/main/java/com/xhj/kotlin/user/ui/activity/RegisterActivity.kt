package com.xhj.kotlin.user.ui.activity

import android.os.Bundle
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.presenter.RegisterPresenter
import com.xhj.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: Boolean) {
//        Toast.makeText(this,"注册成功。",Toast.LENGTH_SHORT).show()
        toast("注册成功123！！！")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this

        mRegisterBtn.setOnClickListener {
            mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
        }
    }
}
