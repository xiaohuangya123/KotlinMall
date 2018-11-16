package com.xhj.kotlin.base.ui.activity

import android.os.Bundle
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import com.xhj.kotlin.base.common.AppManager

open class BaseActivity : RxAppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}