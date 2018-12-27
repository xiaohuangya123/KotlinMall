package com.xhj.kotlin.message.provider

import android.content.Context
import cn.jpush.android.api.JPushInterface
import com.alibaba.android.arouter.facade.annotation.Route
import com.xhj.kotlin.provider.PushProvider
import com.xhj.kotlin.provider.router.RouterPath

/**
 * Author: Created by XHJ on 2018/12/24.
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
class PushProviderImpl: PushProvider {

    private var mContext:Context? = null

    override fun init(context: Context?) {
        mContext = context
    }

    override fun getPushId(): String {
        return JPushInterface.getRegistrationID(mContext)
    }
}