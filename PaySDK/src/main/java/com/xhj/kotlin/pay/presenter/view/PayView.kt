package com.xhj.kotlin.pay.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView

/**
 * Author: Created by XHJ on 2018/12/20.
 */
interface PayView:BaseView {
    fun onGetPaySignResult(result: String)
    fun onPayOrderResult(result: Boolean)
}