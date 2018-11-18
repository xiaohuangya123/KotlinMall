package com.xhj.kotlin.user.data.protocol

/**
 * Author: Created by XHJBB on 2018/11/6.
 */
//用户实体类
data class UserInfo(val id:String,
                    val userIcon:String,
                    val userName:String,
                    val userGender:String,
                    val userMobile:String,
                    val userSign:String)