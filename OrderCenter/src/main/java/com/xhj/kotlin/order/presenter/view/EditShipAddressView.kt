package com.xhj.kotlin.order.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView

interface EditShipAddressView : BaseView{
    fun onAddShipAddressResult(result: Boolean)
    fun onEditShipAddressResult(result: Boolean)
}