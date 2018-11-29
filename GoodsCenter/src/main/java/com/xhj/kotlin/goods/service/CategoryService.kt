package com.xhj.kotlin.goods.service

import com.xhj.kotlin.goods.data.protocol.Category
import io.reactivex.Observable

interface CategoryService {
    fun getCategory(parentId: Int ):Observable<MutableList<Category>?>
}