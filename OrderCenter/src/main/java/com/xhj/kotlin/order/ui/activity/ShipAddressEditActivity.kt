package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.injection.component.DaggerShipAddressComponent
import com.xhj.kotlin.order.injection.module.ShipAddressModule
import com.xhj.kotlin.order.presenter.EditShipAddressPresenter
import com.xhj.kotlin.order.presenter.view.EditShipAddressView
import kotlinx.android.synthetic.main.activity_address.*
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/**
 * Author: Created by XHJ on 2018/12/15.
 */
class ShipAddressEditActivity:BaseMvpActivity<EditShipAddressPresenter>(), EditShipAddressView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
    }

    private fun initView() {
        mSaveBtn.onClick {
            if(mShipNameEt.text.isNullOrBlank()){
                toast("收货人不能为空")
                return@onClick
            }
            if(mShipMobileEt.text.isNullOrBlank()){
                toast("电话不能为空")
                return@onClick
            }
            if(mShipAddressEt.text.isNullOrBlank()){
                toast("地址不能为空")
                return@onClick
            }
            mPresenter.addShipAddress(mShipNameEt.text.toString(),
                mShipMobileEt.text.toString(),
                mShipAddressEt.text.toString())
        }
    }

    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }

    override fun injectComponent() {
        DaggerShipAddressComponent.builder()
            .activityComponent(activityComponent)
            .shipAddressModule(ShipAddressModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }
}