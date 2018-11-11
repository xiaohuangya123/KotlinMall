package com.xhj.kotlin.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * Author: Created by XHJBB on 2018/11/11.
 */

@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope