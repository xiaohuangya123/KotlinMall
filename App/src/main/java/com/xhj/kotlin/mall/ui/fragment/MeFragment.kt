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
import com.xhj.kotlin.order.common.OrderConstant
import com.xhj.kotlin.order.common.OrderStatus
import com.xhj.kotlin.order.ui.activity.OrderActivity
import com.xhj.kotlin.order.ui.activity.ShipAddressActivity
import com.xhj.kotlin.provider.common.ProviderConstant
import com.xhj.kotlin.provider.common.afterLogin
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
        mAddressTv.onClick( this )
        mSettingTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
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
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }
            R.id.mAddressTv ->{ startActivity<ShipAddressActivity>() }
            R.id.mSettingTv ->{ startActivity<SettingActivity>()}

            R.id.mWaitPayOrderTv ->{
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to
                        OrderStatus.ORDER_WAIT_PAY)
                }
            }
            R.id.mWaitConfirmOrderTv ->{
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to
                            OrderStatus.ORDER_WAIT_CONFIRM)
                }
            }
            R.id.mCompleteOrderTv ->{
                afterLogin {
                    startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to
                            OrderStatus.ORDER_COMPLETED)
                }
            }
            R.id.mAllOrderTv ->{
                afterLogin {
                    startActivity<OrderActivity>() //没有加参数，默认就是全部订单
                }
            }
        }
    }

}