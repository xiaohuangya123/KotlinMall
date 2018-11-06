package com.xhj.kotlin.base.ui.activity

import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(),BaseView{

    lateinit var mPresenter : T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

}