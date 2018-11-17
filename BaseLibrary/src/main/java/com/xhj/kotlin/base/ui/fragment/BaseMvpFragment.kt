package com.xhj.kotlin.base.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.orhanobut.logger.Logger
import com.xhj.kotlin.base.common.BaseApplication
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.base.injection.component.DaggerActivityComponent
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.LifecycleProviderModule
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView
import javax.inject.Inject
import org.jetbrains.anko.toast

open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ininActivityComponent()
        Logger.d("injectComponent() 为什么要在ininActivityComponent()方法下面才可以，不然闪退")
        injectComponent()
    }
    //用于Activity绑定Component组件
    abstract fun injectComponent()

    private fun ininActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((activity?.application as BaseApplication).appComponent)
            .activityModule(ActivityModule(activity as Activity))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text : String) {
        Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
    }

}