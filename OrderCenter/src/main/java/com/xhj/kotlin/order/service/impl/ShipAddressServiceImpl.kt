package com.xhj.kotlin.order.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.base.ext.convertBoolean
import com.xhj.kotlin.order.data.protocol.ShipAddress
import com.xhj.kotlin.order.data.repository.ShipAddressRepository
import com.xhj.kotlin.order.service.ShipAddressService
import io.reactivex.Observable
import javax.inject.Inject

class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {

    @Inject
    lateinit var repository : ShipAddressRepository

    /*
      添加收货地址
   */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>{
        return repository.addShipAddress(shipUserName, shipUserMobile, shipAddress).convertBoolean()
    }

    /*
      获取收货地址列表
   */
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?>{
        return repository.getShipAddressList().convert()
    }


}