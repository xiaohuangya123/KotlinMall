package com.xhj.kotlin.goods.data.repository

import com.xhj.kotlin.base.data.net.RetrofitFactory
import com.xhj.kotlin.base.data.protocol.BaseResp
import com.xhj.kotlin.goods.data.api.CategoryApi
import com.xhj.kotlin.goods.data.protocol.Category
import com.xhj.kotlin.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
class CategoryRepository @Inject constructor(){

    fun getCategory(parentId: Int ):Observable<BaseResp<MutableList<Category>?>>{
        return RetrofitFactory.instance.create(CategoryApi::class.java)
            .getCategory(GetCategoryReq(parentId))
    }

}