package com.xhj.kotlin.user.injection.module

import com.xhj.kotlin.user.service.UserService
import com.xhj.kotlin.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(userService: UserServiceImpl):UserService{
        return userService
    }
}