package com.xhj.kotlin.base.injection.module

import com.trello.rxlifecycle3.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/11.
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider:LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider() : LifecycleProvider<*> {
        return lifecycleProvider
    }
}