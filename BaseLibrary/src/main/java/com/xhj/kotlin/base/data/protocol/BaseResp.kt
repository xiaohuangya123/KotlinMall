package com.xhj.kotlin.base.data.protocol

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
class BaseResp<out T>(val status:Int, val message:String, val data:T)