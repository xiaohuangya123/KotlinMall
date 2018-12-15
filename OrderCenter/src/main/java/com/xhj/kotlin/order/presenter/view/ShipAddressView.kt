package com.xhj.kotlin.order.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.order.data.protocol.ShipAddress

interface ShipAddressView : BaseView{
    fun onGetShipAddressListResult(result: MutableList<ShipAddress>?)
}