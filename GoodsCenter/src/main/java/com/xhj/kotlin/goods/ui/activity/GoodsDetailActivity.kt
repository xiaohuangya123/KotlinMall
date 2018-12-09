package com.xhj.kotlin.goods.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.google.android.material.tabs.TabLayout
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.event.AddCartEvent
import com.xhj.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import com.xhj.kotlin.provider.common.afterLogin
import com.xhj.kotlin.provider.common.isLogined
import com.xhj.kotlin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * Author: Created by XHJ on 2018/12/8.
 */
class GoodsDetailActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

    }
}