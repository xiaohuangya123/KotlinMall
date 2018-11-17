package com.xhj.kotlin.base.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.xhj.kotlin.base.R
import org.jetbrains.anko.find

/**
 * Author: Created by XHJ on 2018/11/17.
 */
class ProgressLoading private constructor(context: Context, themeResId: Int) : Dialog(context, themeResId) {
    companion object {
        private lateinit var mDialog : ProgressLoading
        //帧动画
        private var animDrawable : AnimationDrawable? = null

        fun create(context: Context) : ProgressLoading{
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            //设置dialog灰度
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp

            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    fun showLoading(){
        super.show()
        animDrawable?.start()
    }

    fun hideLoading(){
        super.dismiss()
        animDrawable?.stop()
    }
}