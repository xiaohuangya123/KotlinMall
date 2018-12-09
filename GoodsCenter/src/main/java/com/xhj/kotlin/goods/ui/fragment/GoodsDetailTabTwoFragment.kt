package com.xhj.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xhj.kotlin.goods.R
import com.xhj.kotlin.base.ui.fragment.BaseFragment

/**
 * Author: Created by XHJ on 2018/11/29.
 */
class GoodsDetailTabTwoFragment: BaseFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container,false)
    }


}