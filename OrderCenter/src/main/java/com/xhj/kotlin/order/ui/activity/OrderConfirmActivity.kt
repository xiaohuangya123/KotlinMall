package com.xhj.kotlin.order.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ext.setVisible
import com.xhj.kotlin.base.ui.activity.BaseMvpActivity
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.event.SelectShipAddressEvent
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderConfirmPresenter
import com.xhj.kotlin.order.presenter.view.OrderConfirmView
import com.xhj.kotlin.order.ui.adapter.OrderGoodsAdapter
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_confirm.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

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
    private var mCurrentOrder:Order? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirm)

        initView()
        initObserve()
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
        mShipView.onClick {
            startActivity<ShipAddressActivity>()
        }
        //确实提交订单
        mSubmitOrderBtn.onClick {
            mCurrentOrder?.let {
                mPresenter.submitOrder(it)
            }
        }
    }

    /**
     *
     */
    private fun initObserve() {
        Bus.observe<SelectShipAddressEvent>()
            .subscribe{
                t: SelectShipAddressEvent ->
                run {
                    mCurrentOrder?.let {
                            it.shipAddress = t.address//t.address为用户选择的address
                        updateAddressView()
                    }
                }

            }
            .registerInBus(this)
    }

    private fun loadData() {
        mPresenter.getOrderById(mOrderId)
    }

    /**
     * 更具ID查询订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        //存储order信息，获取地址相关信息在updateAddressView()方法中使用
        mCurrentOrder = result
        mAdapter.setData(result.orderGoodsList)
        mTotalPriceTv.text = "${YuanFenConverter.changeF2YWithUnit(result.totalPrice)}"
        //初始化收货人地址信息
        updateAddressView()
    }

    /**
     * 提交订单回调
     */
    override fun onSubmitOrderResult(result: Boolean) {
        toast("订单提交成功")
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder()
            .activityComponent(activityComponent)
            .orderModule(OrderModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }

    private fun updateAddressView() {
        mCurrentOrder?.let {
            if(it.shipAddress == null){
                mSelectShipTv.setVisible(true)//选择收货人
                mShipView.setVisible(false)
            }else{
                mSelectShipTv.setVisible(false)//选择收货人
                mShipView.setVisible(true)

                mShipNameTv.text = it.shipAddress!!.shipUserName + "   "+
                        it.shipAddress!!.shipUserMobile
                mShipAddressTv.text = it.shipAddress!!.shipAddress
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}