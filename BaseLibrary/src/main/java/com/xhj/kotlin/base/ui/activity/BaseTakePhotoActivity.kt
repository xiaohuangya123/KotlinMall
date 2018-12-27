package com.xhj.kotlin.base.ui.activity

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.xhj.kotlin.base.common.BaseApplication
import com.xhj.kotlin.base.injection.component.ActivityComponent
import com.xhj.kotlin.base.injection.component.DaggerActivityComponent
import com.xhj.kotlin.base.injection.module.ActivityModule
import com.xhj.kotlin.base.injection.module.LifecycleProviderModule
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.presenter.view.BaseView
import com.xhj.kotlin.base.utils.DateUtils
import com.xhj.kotlin.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

open abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView, TakePhoto.TakeResultListener  {

    lateinit var activityComponent: ActivityComponent
    lateinit var mLoadingDialog : ProgressLoading

    lateinit var mTakePhoto: TakePhoto
    lateinit var mTempFile: File

    @Inject
    lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ininActivityComponent()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
        //应用ARouter映射变量时需要进行注册
        ARouter.getInstance().inject(this)

        mTakePhoto = TakePhotoImpl(this,this)
        mTakePhoto.onCreate(savedInstanceState)
    }
    //用于Activity绑定Component组件
    abstract fun injectComponent()

    private fun ininActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((application as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }

    //弹窗选择头像
    protected fun showAlertView(){
        AlertView("选择图片", null, "取消", null,
            arrayOf("拍照", "相册"), this
            , AlertView.Style.ActionSheet, object: OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),false)
                    when(position){
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }
                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }
            }).show()
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("takePhoto", result?.image?.originalPath)
        Log.d("takePhoto", result?.image?.compressPath)
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
    }

    fun createTempFile(){
        val tempFileName = "${DateUtils.curTime}.png"
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        this.mTempFile = File(filesDir, tempFileName)
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text : String) {
        toast(text)
    }

}