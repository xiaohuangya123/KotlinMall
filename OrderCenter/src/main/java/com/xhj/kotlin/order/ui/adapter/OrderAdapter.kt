package com.xhj.kotlin.order.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xhj.kotlin.base.ext.loadUrl
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ext.setVisible
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.common.OrderStatus
import com.xhj.kotlin.order.data.protocol.Order
import kotlinx.android.synthetic.main.layout_order_item.view.*
import org.jetbrains.anko.dip

/*
    订单列表数据适配
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {

    var listener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_order_item, parent,false)
        return ViewHolder(view)
    }

    /*
        绑定数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        var mTotalCount = 0
        if(model.orderGoodsList.size == 1){//订单只有一个商品也就是单商品显示
            holder.itemView.mMultiGoodsView.setVisible(false)
            holder.itemView.mSingleGoodsView.setVisible(true)
            val orderGoods = model.orderGoodsList[0]
            holder.itemView.mGoodsIconIv.loadUrl(orderGoods.goodsIcon)
            holder.itemView.mGoodsDescTv.text = orderGoods.goodsDesc
            holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGoods.goodsPrice)
            holder.itemView.mGoodsCountTv.text = "x${orderGoods.goodsCount}"
            mTotalCount = orderGoods.goodsCount
        }else{//订单有多个商品的展示， 之显示商品图标
            //第一步 单商品视图隐藏, 多商品试图显示
            holder.itemView.mSingleGoodsView.setVisible(false)
            holder.itemView.mMultiGoodsView.setVisible(true)
            holder.itemView.mMultiGoodsView.removeAllViews()//每次加载前先清除，否则商品会重复加载
            //第二步 构建商品图标
            for (orderGoods in model.orderGoodsList){
                val imageView = ImageView(mContext)
                val lp =
                    ViewGroup.MarginLayoutParams(mContext.dip(60.0f),mContext.dip(60.0f))
                lp.rightMargin = mContext.dip(15f)
                imageView.layoutParams = lp
                imageView.loadUrl(orderGoods.goodsIcon)
                //第三步 把构建好的商品图标加到多商品视图中
                holder.itemView.mMultiGoodsView.addView(imageView)
                mTotalCount += orderGoods.goodsCount
            }
        }

        //订单商品总数，总价
        holder.itemView.mOrderInfoTv.text = "合计${mTotalCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"

        when(model.orderStatus){
            OrderStatus.ORDER_WAIT_PAY ->{
                holder.itemView.mOrderStatusNameTv.text = "待支付"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_red))
                setOptVisible(false, true, true, holder)
            }
            OrderStatus.ORDER_WAIT_CONFIRM ->{
                holder.itemView.mOrderStatusNameTv.text = "待确认"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_blue))
                setOptVisible(true, false, true, holder)
            }
            OrderStatus.ORDER_COMPLETED ->{
                holder.itemView.mOrderStatusNameTv.text = "已完成"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_yellow))
                setOptVisible(false, false, false, holder)
            }
            OrderStatus.ORDER_CANCELED ->{
                holder.itemView.mOrderStatusNameTv.text = "已取消"
                holder.itemView.mOrderStatusNameTv.setTextColor(mContext.resources.getColor(R.color.common_gray))
                setOptVisible(false, false, false, holder)
            }
        }

        //设置按钮监听事件
        holder.itemView.mConfirmBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CONFIRM, model)
            }
        }

        holder.itemView.mPayBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_PAY, model)
            }
        }

        holder.itemView.mCancelBtn.onClick {
            listener?.let {
                it.onOptClick(OrderConstant.OPT_ORDER_CANCEL, model)
            }
        }

    }
    //设置确认收货，去支付，取消订单按钮的显示与否
    private fun setOptVisible(confirmVisible: Boolean, waitPayVisible: Boolean,
                              cancelVisible: Boolean, holder: ViewHolder) {
        holder.itemView.mConfirmBtn.setVisible(confirmVisible)
        holder.itemView.mPayBtn.setVisible(waitPayVisible)
        holder.itemView.mCancelBtn.setVisible(cancelVisible)

        if(confirmVisible or waitPayVisible or cancelVisible){
            holder.itemView.mBottomView.setVisible(true)//三个按钮父控件
        }else{
            holder.itemView.mBottomView.setVisible(false)
        }
    }
    //面向接口编程，定义接口，在OrderFragment中具体实现接口
    interface OnOptClickListener{
        fun onOptClick(optType:Int, order: Order)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
