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
class ActivityModule(private val activity:Activity) {

    @Provides
    fun providesActivity() : Activity {
        return activity
    }
}