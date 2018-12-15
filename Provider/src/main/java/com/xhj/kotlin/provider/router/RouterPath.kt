package com.xhj.kotlin.provider.router

/**
 * Author: Created by XHJ on 2018/12/9.
 */

object RouterPath{
    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/userCenter/login"
        }
    }

    class OrderCenter{
        companion object {
            const val PATH_ORDER_CONFIRM = "/orderCenter/confirm"
        }
    }
}