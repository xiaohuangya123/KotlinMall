package com.xhj.kotlin.base.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.xhj.kotlin.base.common.BaseApplication
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.base.injection.component.DaggerActivityComponent
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.LifecycleProviderModule
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var activityComponent: ActivityComponent
    lateinit var mLoadingDialog : ProgressLoading

    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ininActivityComponent()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
        //应用ARouter映射变量时需要进行注册
        ARouter.getInstance().inject(this)
    }
    //用于Activity绑定Component组件
    abstract fun injectComponent()

    private fun ininActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text : String) {
        toast(text)
    }

}