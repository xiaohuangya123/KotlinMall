package com.xhj.kotlin.user.injection.component

import com.xhj.kotlin.base.injection.PerComponentScope
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.ui.activity.ForgetPwdActivity
import com.xhj.kotlin.user.ui.activity.LoginActivity
import com.xhj.kotlin.user.ui.activity.RegisterActivity
import com.xhj.kotlin.user.ui.activity.ResetPwdActivity
import dagger.Component

/**
 * Author: Created by XHJBB on 2018/11/10.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class))
interface UserComponent {
    fun inject(activity:RegisterActivity)
    fun inject(activity:LoginActivity)
    fun inject(activity:ForgetPwdActivity)
    fun inject(activity:ResetPwdActivity)
}