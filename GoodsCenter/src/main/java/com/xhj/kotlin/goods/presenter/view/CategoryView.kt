package com.xhj.kotlin.goods.presenter.view

import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.goods.data.protocol.Category

interface CategoryView : BaseView{
    fun onGetCategoryResult(result: MutableList<Category>?)
}