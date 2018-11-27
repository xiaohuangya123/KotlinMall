package com.xhj.kotlin.base.widgets

import android.content.Context
import android.widget.ImageView
import com.xhj.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 * Author: Created by XHJ on 2018/11/26.
 */
class BannerImageLoader: ImageLoader() {

    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}