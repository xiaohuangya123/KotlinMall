package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderConfirmPresenter
import com.xhj.kotlin.order.presenter.view.OrderConfirmView
import com.xhj.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity

/**
 * Author: Created by XHJ on 2018/12/15.
 */
@Route(path = RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
class OrderConfirmActivity:BaseMvpActivity<OrderConfirmPresenter>(), OrderConfirmView {

    //应用ARouter映射变量，在kotlin还需要加@JvmField注解
    @Autowired(name = ProviderConstant.KEY_ORDER_ID)
    @JvmField
    var mOrderId:Int = 0
    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        loadData()
    }

    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
        //选择收货地址
        mSelectShipTv.onClick {
            startActivity<ShipAddressActivity>()
        }
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    override fun onGetOrderByIdResult(result: Order) {
        mAdapter.setData(result.orderGoodsList)
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