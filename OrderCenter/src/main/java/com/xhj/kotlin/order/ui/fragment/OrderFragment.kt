package com.xhj.kotlin.order.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kennyc.view.MultiStateView
import com.xhj.kotlin.base.ext.startLoading
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.ui.fragment.BaseMvpFragment
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.data.protocol.Order
import com.xhj.kotlin.order.injection.component.DaggerOrderComponent
import com.xhj.kotlin.order.injection.module.OrderModule
import com.xhj.kotlin.order.presenter.OrderListPresenter
import com.xhj.kotlin.order.presenter.view.OrderListView
import com.xhj.kotlin.order.ui.activity.OrderDetailActivity
import com.xhj.kotlin.order.ui.adapter.OrderAdapter
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_order.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Author: Created by XHJ on 2018/12/16.
 */
class OrderFragment:BaseMvpFragment<OrderListPresenter>(), OrderListView {

    private lateinit var mAdapter:OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ininView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /**
     * 初始化试图
     */
    private fun ininView() {
        mOrderRv.layoutManager = LinearLayoutManager(activity)
        mAdapter = OrderAdapter(activity as Activity)
        mOrderRv.adapter = mAdapter
        //面向接口编程思想，接口的具体实现
        mAdapter.listener = object : OrderAdapter.OnOptClickListener{
            override fun onOptClick(optType: Int, order: Order) {
                when(optType){
                    OrderConstant.OPT_ORDER_CONFIRM -> {//确认收获按钮
                        mPresenter.confirmOrder(order.id)
                    }
                    OrderConstant.OPT_ORDER_PAY -> {//去支付按钮
                        ARouter.getInstance()
                            .build(RouterPath.PaySDK.PATH_PAY)
                            .withInt(ProviderConstant.KEY_ORDER_ID,order.id)
                            .withLong(ProviderConstant.KEY_ORDER_PRICE, order.totalPrice)
                            .navigation()
                    }
                    OrderConstant.OPT_ORDER_CANCEL -> {//取消订单按钮
                        showCancelDialog(order.id)
                    }
                }
            }
        }

        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Order>{
            override fun onItemClick(item: Order, position: Int) {
                startActivity<OrderDetailActivity>(ProviderConstant.KEY_ORDER_ID to item.id)
            }

        })
    }
    //弹出dialog 确认是否取消订单
    private fun showCancelDialog(orderId: Int) {
        AlertView("取消订单", "确定取消该订单？", "取消", null,
            arrayOf("确定"), activity, AlertView.Style.Alert,
            object: OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    if(position == 0){
                        mPresenter.cancelOrder(orderId)
                    }
                }
            }).show()
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getOrderList(arguments!!.getInt(OrderConstant.KEY_ORDER_STATUS, -1))
    }

    /*
       根据状态查询订单列表 回调
    */
    override fun onGetOrderListResult(result: MutableList<Order>?) {
        if(result != null && result.size >0){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /**
     * 取消订单 回调
     */
    override fun oncCancelOrderResult(result: Boolean) {
        toast("取消订单成功")
        loadData()
    }
    /**
     * 确认收货订单 回调
     */
    override fun onConfirmOrderResult(result: Boolean) {
        toast("确认收货订单成功")
        loadData()
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