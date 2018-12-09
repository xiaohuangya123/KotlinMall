package com.xhj.kotlin.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.xhj.kotlin.base.common.AppManager
import org.jetbrains.anko.find

open class BaseActivity : RxAppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    val contentView:View
        get(){
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }
}