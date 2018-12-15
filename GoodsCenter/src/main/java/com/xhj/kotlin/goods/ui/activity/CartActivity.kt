package com.xhj.kotlin.goods.ui.activity

import android.os.Bundle
import com.xhj.kotlin.base.ui.activity.BaseActivity
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.goods.ui.fragment.CartFragment

/**
 * Author: Created by XHJ on 2018/12/15.
 */
//因为fragment不可以单独显示，此activity相当于加壳
class CartActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //headerbar左侧显示返回按键
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartFragment).setBackVisible(true)
    }
}