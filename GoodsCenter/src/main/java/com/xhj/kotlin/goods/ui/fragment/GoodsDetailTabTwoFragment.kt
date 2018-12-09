package com.xhj.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.xhj.kotlin.base.ext.loadUrl
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.base.ui.fragment.BaseFragment
import com.xhj.kotlin.goods.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class GoodsDetailTabTwoFragment: BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    /*
        初始化监听，商品详情获取成功后，加载当前页面
     */
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
            .subscribe {
                    t: GoodsDetailImageEvent ->
                run {
                    mGoodsDetailOneIv.loadUrl(t.imgOne)
                    mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                }
            }
            .registerInBus(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }


}