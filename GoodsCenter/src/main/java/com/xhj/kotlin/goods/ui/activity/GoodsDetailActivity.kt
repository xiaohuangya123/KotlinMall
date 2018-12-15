package com.xhj.kotlin.goods.ui.activity

import android.os.Bundle
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.google.android.material.tabs.TabLayout
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.common.GoodsConstant
import com.xhj.kotlin.goods.event.AddCartEvent
import com.xhj.kotlin.goods.event.UpdateCartSizeEvent
import com.xhj.kotlin.goods.ui.adapter.GoodsDetailVpAdapter
import com.xhj.kotlin.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import q.rorbin.badgeview.QBadgeView

/**
 * Author: Created by XHJ on 2018/12/8.
 */
class GoodsDetailActivity: BaseActivity() {

    private lateinit var mCartBadge:QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        //加入购物车点击事件
        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }
        //购物车点击事件
        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }
        //返回按键
        mLeftIv.onClick {
            finish()
        }

        mCartBadge = QBadgeView(this)
    }

    private fun loadCartSize() {
        setCartBadge()
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
            .subscribe{
                setCartBadge()
            }.registerInBus(this)
    }

    private fun setCartBadge() {
        mCartBadge.badgeGravity = Gravity.END or Gravity.TOP
        mCartBadge.setGravityOffset(22f,-2f ,true)
        mCartBadge.setBadgeTextSize(8f, true)
        mCartBadge.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}