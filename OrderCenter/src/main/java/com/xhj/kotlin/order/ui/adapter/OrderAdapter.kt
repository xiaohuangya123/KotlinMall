package com.xhj.kotlin.order.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhj.kotlin.base.ext.loadUrl
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.utils.YuanFenConverter
import com.xhj.kotlin.order.R
import com.xhj.kotlin.order.data.protocol.Order
import kotlinx.android.synthetic.main.layout_order_item.view.*

/*
    订单列表数据适配
 */
class OrderAdapter(context: Context) : BaseRecyclerViewAdapter<Order, OrderAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_order_item, parent,false)
        return ViewHolder(view)
    }

    /*
        绑定数据
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        if(model.orderGoodsList.size == 1){//订单只有一个商品也就是单商品显示
            val orderGood = model.orderGoodsList[0]
            holder.itemView.mGoodsIconIv.loadUrl(orderGood.goodsIcon)
            holder.itemView.mGoodsDescTv.text = orderGood.goodsDesc
            holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(orderGood.goodsPrice)
            holder.itemView.mGoodsCountTv.text = "x${orderGood.goodsCount}"
            holder.itemView.mOrderInfoTv.text = "合计${orderGood.goodsCount}件商品，总价${YuanFenConverter.changeF2YWithUnit(model.totalPrice)}"
        }else{

        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
