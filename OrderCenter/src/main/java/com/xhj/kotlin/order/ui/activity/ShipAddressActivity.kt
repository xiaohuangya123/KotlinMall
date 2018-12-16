package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.data.protocol.ShipAddress
import com.xhj.kotlin.order.injection.component.DaggerShipAddressComponent
import com.xhj.kotlin.order.injection.module.ShipAddressModule
import com.xhj.kotlin.order.presenter.ShipAddressPresenter
import com.xhj.kotlin.order.presenter.view.ShipAddressView
import com.xhj.kotlin.order.ui.adapter.ShipAddressAdapter
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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

        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener{
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<ShipAddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "确定删除该地址？", "取消", null,
                    arrayOf("确定"), this@ShipAddressActivity
                    , AlertView.Style.Alert, object: OnItemClickListener {
                        override fun onItemClick(o: Any?, position: Int) {
                            if(position == 0){
                                mPresenter.deleteShipAddress(address.id)
                            }
                        }
                    }).show()
            }
        }

        mAddAddressBtn.onClick {
            startActivity<ShipAddressEditActivity>()
        }
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    /**
     * 获取收货地址列表回调
     */
    override fun onGetShipAddressListResult(result: MutableList<ShipAddress>?) {
        if(result != null && result.size>0 ){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 修改收货地址回调
     */
    override fun onEditShipAddressResult(Result: Boolean) {
        //修改默认地址成功后，从新加载地址数据
        loadData()
    }

    /**
     * 删除收获地址成功
     */
    override fun onDeleteShipAddressResult(Result: Boolean) {
        //删除地址后，从新加载地址数据
        loadData()
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