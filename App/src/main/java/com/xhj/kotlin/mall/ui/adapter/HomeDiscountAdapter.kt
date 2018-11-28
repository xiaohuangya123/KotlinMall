package com.xhj.kotlin.mall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhj.kotlin.base.ui.adapter.BaseRecyclerViewAdapter
import com.xhj.kotlin.base.utils.GlideUtils
import com.xhj.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * Author: Created by XHJ on 2018/11/27.
 */
class HomeDiscountAdapter(context:Context): BaseRecyclerViewAdapter<String, HomeDiscountAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.layout_home_discount_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext, dataList[position], holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountBeforeTv.text = "$200"
        holder.itemView.mDiscountAfterTv.text = "$99"
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            //消除锯齿
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}