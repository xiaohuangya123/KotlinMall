package com.xhj.kotlin.goods.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.presenter.view.CategoryView
import com.xhj.kotlin.goods.service.CategoryService
import javax.inject.Inject

class CategoryPresenter @Inject constructor(): BasePresenter<CategoryView>(){

    @Inject
    lateinit var categoryService : CategoryService

    fun getCategory(parentId: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId)
            .execute(object : BaseObserver<MutableList<Category>?>(mView){
                override fun onNext(t: MutableList<Category>?) {
                    mView.onGetCategoryResult(t)
                }
            },lifecycleProvider)
    }
}

