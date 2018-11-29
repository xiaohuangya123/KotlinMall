package com.xhj.kotlin.goods.injection.module

import com.xhj.kotlin.goods.service.CategoryService
import com.xhj.kotlin.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class CategoryrModule {

    @Provides
    fun providesCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}