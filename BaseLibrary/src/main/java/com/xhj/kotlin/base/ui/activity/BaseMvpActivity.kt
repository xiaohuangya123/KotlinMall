package com.xhj.kotlin.base.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.xhj.kotlin.base.common.BaseApplication
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.base.injection.component.AppComponent
import com.xhj.kotlin.base.injection.component.DaggerActivityComponent
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.LifecycleProviderModule
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ininActivityComponent()
    }

    private fun ininActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()

    }

}