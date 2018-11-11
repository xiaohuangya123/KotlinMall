package com.xhj.kotlin.base.injection.module

import android.app.Activity
import android.content.Context
import com.xhj.kotlin.base.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext() :Context{
        return context
    }
}