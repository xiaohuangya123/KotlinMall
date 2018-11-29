package com.xhj.kotlin.goods.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.data.repository.CategoryRepository
import com.xhj.kotlin.goods.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor() :CategoryService{


    @Inject
    lateinit var repository : CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return repository.getCategory(parentId).convert()
    }
}