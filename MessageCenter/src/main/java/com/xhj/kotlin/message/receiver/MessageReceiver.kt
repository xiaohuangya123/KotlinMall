package com.xhj.kotlin.message.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jpush.android.api.JPushInterface
import androidx.core.app.NotificationCompat.getExtras
import android.os.Bundle
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.router.RouterPath
import org.json.JSONObject


/**
 * Author: Created by XHJ on 2018/12/23.
 */
class MessageReceiver:BroadcastReceiver() {

    private val TAG = "MessageReceiver"

    override fun onReceive(context: Context, intent: Intent) {

        val bundle = intent.extras
        Log.d(TAG, "onReceive - " + intent.action + ", extras: " + bundle )

        if (JPushInterface.ACTION_REGISTRATION_ID == intent.action) {
            Log.d(TAG, "JPush 用户注册成功")

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action) {
            Log.d(TAG, "接受到推送下来的自定义消息")
            Toast.makeText(context, bundle.getString(JPushInterface.EXTRA_MESSAGE), Toast.LENGTH_LONG).show()

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action) {
            Log.d(TAG, "接受到推送下来的通知")
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action) {
            Log.d(TAG, "用户点击打开了通知")
            val extra = bundle.getString(JPushInterface.EXTRA_EXTRA)
            val json = JSONObject(extra)
            val orderId = json.getInt("orderId")
            ARouter.getInstance()
                .build(RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
                .withInt(ProviderConstant.KEY_ORDER_ID, orderId)
                .navigation()
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.action)
        }
    }
}