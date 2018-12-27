package com.xhj.kotlin.user.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.qiniu.android.storage.UploadManager
import com.tbruyelle.rxpermissions2.RxPermissions
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.activity.BaseTakePhotoActivity
import com.xhj.kotlin.base.utils.GlideUtils
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.user.R
import com.xhj.kotlin.user.data.protocol.UserInfo
import com.xhj.kotlin.user.injection.component.DaggerUserComponent
import com.xhj.kotlin.user.injection.module.UserModule
import com.xhj.kotlin.user.presenter.UserInfoPresenter
import com.xhj.kotlin.user.presenter.view.UserInfoView
import com.xhj.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 * 用户界面
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {

    private val mUploadManager: UploadManager by lazy { UploadManager() }
    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon:String? = null
    private var mUserName:String? = null
    private var mUserMobile:String? = null
    private var mUserGender:String? = null
    private var mUserSign:String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        //申请相机，相册权限
        requestPermissions()

        initView()
        initData()
    }

    //初始化视图
    private fun initView() {
        mUserIconIv.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!
                , mUserNameEt.text.toString()?:""
                , if(mGenderMaleRb.isChecked) "0" else "1"
                , mUserSignEt.text.toString()?:"")
        }
    }
    //初始化数据
    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != ""){
            GlideUtils.loadUrlImage(this,mUserIcon!!,mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        }
        else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        //保存修改后的用户信息
        UserPrefsUtils.putUserInfo(result)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder()
            .activityComponent(activityComponent)
            .userModule(UserModule())
            .build()
            .inject(this)

        mPresenter.mView = this
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode,resultCode, data)
    }



//    @SuppressLint("CheckResult")
    @SuppressLint("CheckResult")
    private fun requestPermissions(){
        val rxPermissions = RxPermissions(this)
        rxPermissions
            .request(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .subscribe{
                if (it) {
                    // All requested permissions are granted
                } else {
                    // At least one permission is denied
                }
            }
    }

    override fun onGetUploadTokenResult(result: String) {
        //由于千牛的图片存储服务器不可用 暂时 注释掉上传图片部分 后续自己申请后上传图片
//        mUploadManager.put(mLocalFileUrl,null,result, object : UpCompletionHandler{
//            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
//                mRemoteFile = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
//                Log.d("test", mRemoteFile)
//                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFile!!, mUserIconIv)
//            }
//
//        },null)
    }
}
