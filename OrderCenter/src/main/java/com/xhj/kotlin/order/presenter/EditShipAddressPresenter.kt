package com.xhj.kotlin.order.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.order.presenter.view.EditShipAddressView
import com.xhj.kotlin.order.service.ShipAddressService
import javax.inject.Inject

class EditShipAddressPresenter @Inject constructor(): BasePresenter<EditShipAddressView>(){

    @Inject
    lateinit var shipAddressService : ShipAddressService

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress)
            .execute(object : BaseObserver<Boolean>(mView){
                override fun onNext(t: Boolean) {
                    mView.onAddShipAddressResult(t)
                }
            },lifecycleProvider)
    }
}

