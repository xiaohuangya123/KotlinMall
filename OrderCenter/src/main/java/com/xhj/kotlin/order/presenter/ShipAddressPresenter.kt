package com.xhj.kotlin.order.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.order.data.protocol.ShipAddress
import com.xhj.kotlin.order.presenter.view.ShipAddressView
import com.xhj.kotlin.order.service.ShipAddressService
import javax.inject.Inject

class ShipAddressPresenter @Inject constructor(): BasePresenter<ShipAddressView>(){

    @Inject
    lateinit var shipAddressService : ShipAddressService

    /*
        添加收货地址
     */
    fun getShipAddressList(){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList()
            .execute(object : BaseObserver<MutableList<ShipAddress>?>(mView){
                override fun onNext(t: MutableList<ShipAddress>?) {
                    mView.onGetShipAddressListResult(t)
                }
            },lifecycleProvider)
    }
}

