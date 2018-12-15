package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderConfirmPresenter
import com.xhj.kotlin.order.presenter.view.OrderConfirmView
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.toast

/**
 * Author: Created by XHJ on 2018/12/15.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity:BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    private var mOrderId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        mOrderId = intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1)

        initView()

    }

    private fun initView() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mTotalPriceTv.text = "${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
            .activityComponent(activityComponent)
            .orderModule(OrderModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }
}