package com.xhj.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xhj.kotlin.base.utils.AppPrefsUtils
import com.xhj.kotlin.base.ext.loadUrl
import com.xhj.kotlin.base.ext.onClick
import com.xhj.kotlin.base.ui.fragment.BaseFragment
import com.xhj.kotlin.mall.R
import com.xhj.kotlin.mall.ui.activity.SettingActivity
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.common.isLogined
import com.xhj.kotlin.user.ui.activity.LoginActivity
import com.xhj.kotlin.user.ui.activity.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity


/**
 * Author: Created by XHJ on 2018/11/26.
 */
class MeFragment: BaseFragment(),View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        if(isLogined()){
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if(userIcon.isNotEmpty()){
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }else{
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mUserIconIv, R.id.mUserNameTv ->{
                if(isLogined()){
                    startActivity<UserInfoActivity>()
                }else{
                    startActivity<LoginActivity>()
                }
            }

            R.id.mSettingTv ->{
                startActivity<SettingActivity>()
            }
        }
    }

}