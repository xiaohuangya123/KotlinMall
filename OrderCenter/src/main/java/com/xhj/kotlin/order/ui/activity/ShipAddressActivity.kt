package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.ShipAddress
import com.xhj.kotlin.order.injection.component.DaggerShipAddressComponent
import com.xhj.kotlin.order.injection.module.ShipAddressModule
import com.xhj.kotlin.order.presenter.ShipAddressPresenter
import com.xhj.kotlin.order.presenter.view.ShipAddressView
import com.xhj.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity

/**
 * Author: Created by XHJ on 2018/12/15.
 */
class ShipAddressActivity:BaseMvpActivity<ShipAddressPresenter>(), ShipAddressView {

    private lateinit var mAdapter:ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter

        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    override fun onGetShipAddressListResult(result: MutableList<ShipAddress>?) {
        if(result != null && result.size>0 ){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
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