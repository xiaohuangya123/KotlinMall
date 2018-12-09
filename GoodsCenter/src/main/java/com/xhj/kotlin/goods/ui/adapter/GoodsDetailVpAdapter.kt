package com.xhj.kotlin.goods.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.xhj.kotlin.goods.ui.fragment.GoodsDetailTabOneFragment
import com.xhj.kotlin.goods.ui.fragment.GoodsDetailTabTwoFragment

/**
 * Author: Created by XHJ on 2018/12/8.
 */
class GoodsDetailVpAdapter(fm:FragmentManager, context: Context): FragmentPagerAdapter(fm) {

    private val titles = arrayOf("商品","详情")

    override fun getItem(position: Int): Fragment {
        return if(position == 0){
            GoodsDetailTabOneFragment()
        }else{
            GoodsDetailTabTwoFragment()
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}