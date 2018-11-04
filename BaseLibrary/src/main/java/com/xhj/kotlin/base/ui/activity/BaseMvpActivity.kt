package com.xhj.kotlin.base.ui.activity

import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(),BaseView{

    lateinit var mPresenter : T

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}