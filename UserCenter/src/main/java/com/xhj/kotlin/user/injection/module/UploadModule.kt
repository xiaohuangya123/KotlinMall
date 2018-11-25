package com.xhj.kotlin.user.injection.module

import com.xhj.kotlin.user.service.UploadService
import com.xhj.kotlin.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(uploadService: UploadServiceImpl):UploadService{
        return uploadService
    }
}