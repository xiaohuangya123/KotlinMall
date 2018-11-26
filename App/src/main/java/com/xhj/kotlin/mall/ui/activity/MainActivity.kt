package com.xhj.kotlin.mall.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xhj.kotlin.mall.R
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavBar.checkCartBadge(20)
        mBottomNavBar.checkMsgBadge(false)

        Observable.timer(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mBottomNavBar.checkMsgBadge(true)
            }

        Observable.timer(5, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                mBottomNavBar.checkCartBadge(0)
            }

    }
}
